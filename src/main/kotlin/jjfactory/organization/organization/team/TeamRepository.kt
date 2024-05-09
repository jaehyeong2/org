package jjfactory.organization.organization.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository: JpaRepository<Team, Long> {
    fun findAllByOrganizationId(organizationId: Long): List<Team>
}