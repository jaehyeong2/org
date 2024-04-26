package jjfactory.organization.organization

import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository: JpaRepository<Organization, Long> {
}