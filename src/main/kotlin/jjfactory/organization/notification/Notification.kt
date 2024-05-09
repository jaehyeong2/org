package jjfactory.organization.notification

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class Notification(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val sourceUserId: Long,

    //fix되면 to enum
    val type: String,

    var isRead: Boolean = false,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
) {
}