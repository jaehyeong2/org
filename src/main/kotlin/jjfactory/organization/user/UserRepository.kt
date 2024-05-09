package jjfactory.organization.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,Long> {
    fun existsByActivateToken(token: String): Boolean
    fun findAllByOrganizationIdAndIsActivatedIsTrue(organizationId: Long): List<User>
    fun findAllByIdInAndIsActivatedIsTrue(ids: List<Long>): List<User>
}