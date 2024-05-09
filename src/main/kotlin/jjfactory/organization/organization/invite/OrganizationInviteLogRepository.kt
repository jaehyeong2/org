package jjfactory.organization.organization.invite

import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationInviteLogRepository : JpaRepository<OrganizationInviteLog, Long> {
}