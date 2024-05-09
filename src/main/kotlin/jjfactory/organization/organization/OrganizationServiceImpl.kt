package jjfactory.organization.organization

import jjfactory.organization.external.MailSender
import jjfactory.organization.organization.invite.OrganizationInviteLog
import jjfactory.organization.organization.invite.OrganizationInviteLogRepository
import jjfactory.organization.organization.team.TeamRepository
import jjfactory.organization.user.User
import jjfactory.organization.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class OrganizationServiceImpl(
    private val organizationRepository: OrganizationRepository,
    private val inviteLogRepository: OrganizationInviteLogRepository,
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val mailSender: MailSender
) : OrganizationService {
    override fun create(request: OrganizationDto.Create): Long {
        val initOrganization = request.toEntity()
        val organization = organizationRepository.save(initOrganization)

        val managerRequest = request.manager

        val initUser = User(
            lastName = managerRequest.lastName,
            firstName = managerRequest.firstName,
            phone = managerRequest.phone,
            email = managerRequest.email,
            organizationId = organization.id!!,
            activateToken = generateToken()
        )

        val user = userRepository.save(initUser)

        mailSender.sendManagerMail(
            token = user.activateToken,
            email = user.email
        )

        return organization.id
    }

    private fun generateToken(): String {
        var token = UUID.randomUUID().toString().replace("-", "")

        while (true) {
            if (userRepository.existsByActivateToken(token)) break
            token = UUID.randomUUID().toString().replace("-", "")
        }

        return token
    }

    override fun sendInviteMailToUser(loginUserId: Long, request: OrganizationDto.InviteMailRequest) {
        val loginUser = validateAdminRole(loginUserId)

        val initLog = OrganizationInviteLog(
            organizationId = loginUser.organizationId,
            email = request.email,
            role = request.role
        )

        inviteLogRepository.save(initLog)

        mailSender.sendInviteMail(
            email = request.email,
            role = request.role.toString()
        )
    }

    override fun delete(id: Long) {
        val organization = organizationRepository.findByIdOrNull(id) ?: throw NotFoundException()

        val teams = teamRepository.findAllByOrganizationId(organizationId = organization.id!!)
        val users = userRepository.findAllByOrganizationIdAndIsActivatedIsTrue(organizationId = organization.id)

        if (teams.isNotEmpty() || users.isNotEmpty()) throw IllegalArgumentException("이미 활성화된 기업입니다.")

        organizationRepository.delete(organization)
    }

    private fun validateAdminRole(loginUserId: Long): User {
        val loginUser = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()
        loginUser.validateAdminRole()

        return loginUser
    }
}