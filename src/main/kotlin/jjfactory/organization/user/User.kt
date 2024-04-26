package jjfactory.organization.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
    var email: String,
    var phone: String,

    var role: Role,

    @CreationTimestamp
    val createDt: LocalDateTime? = null,
    @UpdateTimestamp
    val updateDt: LocalDateTime? = null,
) {
}