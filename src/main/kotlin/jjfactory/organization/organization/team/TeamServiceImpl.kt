package jjfactory.organization.organization.team

import jjfactory.organization.exception.AccessDeniedException
import jjfactory.organization.user.Role
import jjfactory.organization.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.log

@Transactional
@Service
class TeamServiceImpl(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val teamUserRepository: TeamUserRepository
) : TeamService {
    override fun createTeam(loginUserId: Long, request: TeamDto.CreateRequest): Long {
        validateAdminRole(loginUserId)
        return teamRepository.save(request.toEntity()).id!!
    }

    override fun deleteUserFromTeam(loginUserId: Long, userId: Long, teamId: Long){
        validateAdminRole(loginUserId)
        teamUserRepository.deleteByUserIdAndTeamId(userId, teamId)
    }

    private fun validateAdminRole(loginUserId: Long) {
        val loginUser = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()
        if (loginUser.role != Role.ADMIN) throw AccessDeniedException()
    }

    override fun deleteTeam(loginUserId: Long, id: Long) {
        validateAdminRole(loginUserId)

        val team = teamRepository.findByIdOrNull(id) ?: throw NotFoundException()
        val teamUsers = teamUserRepository.findAllByTeamId(teamId = team.id!!)

        if (teamUsers.isNotEmpty()){
            throw IllegalArgumentException("조직원이 존재하는 팀은 삭제 불가")
        }

        teamRepository.deleteById(id)
    }

    override fun addUsersToTeam(loginUserId: Long, requests: List<TeamDto.AddUserRequest>, teamId: Long){
        validateAdminRole(loginUserId)

        val team = teamRepository.findByIdOrNull(teamId) ?: throw NotFoundException()

        requests.forEach {
            val user = userRepository.findByIdOrNull(teamId) ?: throw NotFoundException()

            val initTeamUser = TeamUser(
                teamId = team.id!!,
                userId = user.id!!,
                position = it.position
            )

            teamUserRepository.save(initTeamUser)
        }
    }

    override fun update(loginUserId: Long, id: Long, request: TeamDto.UpdateRequest): Long {
        validateAdminRole(loginUserId)

        val team = teamRepository.findByIdOrNull(id) ?: throw NotFoundException()

        team.update(request.name)
        return team.id!!
    }
}