package jjfactory.organization.organization.team

interface TeamService {
    fun getTeamsByOrganizationId(organizationId: Long): List<TeamDto.ListResponse>
    fun deleteUserFromTeam(loginUserId: Long, userId: Long, teamId: Long)
    fun deleteTeam(loginUserId: Long, id: Long)
    fun addUsersToTeam(loginUserId: Long, requests: List<TeamDto.AddUserRequest>, teamId: Long)
    fun update(loginUserId: Long, id: Long, request: TeamDto.UpdateRequest): Long
    fun createTeam(loginUserId: Long, request: TeamDto.CreateRequest): Long
    fun getDetail(id: Long): TeamDto.DetailResponse
}