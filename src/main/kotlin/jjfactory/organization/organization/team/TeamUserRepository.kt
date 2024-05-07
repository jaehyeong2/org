package jjfactory.organization.organization.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamUserRepository : JpaRepository<TeamUser, Long> {
    fun findAllByTeamId(teamId: Long): List<TeamUser>
    fun findByUserIdAndTeamId(userId: Long, teamId: Long)
    fun deleteByUserIdAndTeamId(userId: Long, teamId: Long)
}