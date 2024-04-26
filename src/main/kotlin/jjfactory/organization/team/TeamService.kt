package jjfactory.organization.team

interface TeamService {
    fun createTeam(request: TeamDto.CreateRequest): Long
    fun delete(id: Long)
    fun update(id: Long, request: TeamDto.UpdateRequest): Long
}