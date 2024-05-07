package jjfactory.organization.organization.team

import jjfactory.organization.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TeamServiceImpl(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val teamUserRepository: TeamUserRepository
) : TeamService {
    override fun createTeam(request: TeamDto.CreateRequest): Long {
        return teamRepository.save(request.toEntity()).id!!
    }

    override fun delete(id: Long) {
        val team = teamRepository.findByIdOrNull(id) ?: throw NotFoundException()
        val teamUsers = teamUserRepository.findAllByTeamId(teamId = team.id!!)

        if (teamUsers.isNotEmpty()){
            //FIXME 익셉션 타입변경
            throw IllegalArgumentException("조직원이 존재하는 팀은 삭제 불가")
        }

        teamRepository.deleteById(id)
    }

    override fun addUsersToTeam(requests: List<TeamDto.AddUserRequest>, teamId: Long){
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

    override fun update(id: Long, request: TeamDto.UpdateRequest): Long {
        val team = teamRepository.findByIdOrNull(id) ?: throw NotFoundException()

        team.update(request.name)
        return team.id!!
    }
}