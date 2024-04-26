package jjfactory.organization.team

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Team(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,
    var parentId: Long,
    val organizationId: Long,

    @CreationTimestamp
    val createDt: LocalDateTime? = null,
    @UpdateTimestamp
    val updateDt: LocalDateTime? = null,
) {
}