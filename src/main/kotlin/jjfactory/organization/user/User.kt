package jjfactory.organization.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jjfactory.organization.exception.AccessDeniedException
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val lastName: String,
    val firstName: String,
    var email: String,
    var phone: String,

    val activateToken: String,

    val organizationId: Long,

    var role: Role = Role.NORMAL,

    var isActivated: Boolean = false,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
) {
    val fullName: String = "$lastName $firstName"

    fun activate(){
        isActivated = true
    }

    fun validateAdminRole(){
        if (role != Role.ADMIN) throw AccessDeniedException()
    }
}