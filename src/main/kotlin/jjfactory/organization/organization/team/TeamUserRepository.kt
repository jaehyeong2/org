package jjfactory.organization.organization.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamUserRepository : JpaRepository<TeamUser, Long> {
    fun findAllByTeamId(teamId: Long): List<TeamUser>
}