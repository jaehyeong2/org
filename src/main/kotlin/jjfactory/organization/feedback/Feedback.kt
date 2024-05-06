package jjfactory.organization.feedback

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Feedback(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val sendUserId: Long,
    val receiveUserId: Long,

    var content: String,

    @CreationTimestamp
    val createDt: LocalDateTime? = null,
    @UpdateTimestamp
    val updateDt: LocalDateTime? = null,
) {

    fun modify(content: String){
        this.content = content
    }
}