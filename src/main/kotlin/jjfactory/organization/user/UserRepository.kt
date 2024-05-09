package jjfactory.organization.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,Long> {
    fun existsByActivateToken(token: String): Boolean
    fun findAllByOrganizationIdAndActivatedIsTrue(organizationId: Long): List<User>
}