package jjfactory.organization.team

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TeamServiceImpl(
    private val teamRepository: TeamRepository
) : TeamService {
    override fun createTeam(request: TeamDto.CreateRequest): Long {
        return teamRepository.save(request.toEntity()).id!!
    }

    override fun delete(id: Long) {
        teamRepository.findByIdOrNull(id) ?: throw NotFoundException()
        teamRepository.deleteById(id)
    }

    override fun update(id: Long, request: TeamDto.UpdateRequest): Long {
        val team = teamRepository.findByIdOrNull(id) ?: throw NotFoundException()

        team.update(request.name)
        return team.id!!
    }
}