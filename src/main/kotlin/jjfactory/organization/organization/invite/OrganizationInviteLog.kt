package jjfactory.organization.organization.invite

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jjfactory.organization.user.Role
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class OrganizationInviteLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val organizationId: Long,
    val email: String,
    val role: Role,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
) {
}