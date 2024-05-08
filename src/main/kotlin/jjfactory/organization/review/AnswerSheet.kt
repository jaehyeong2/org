package jjfactory.organization.review

import jakarta.persistence.*
import jjfactory.organization.exception.AlreadySubmittedReviewException
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class AnswerSheet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,
    val targetUserId: Long,
    val metaId: Long,

    @Enumerated(EnumType.STRING)
    val type: ReviewType,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.TEMP,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,

    var submittedAt: LocalDateTime? = null,
) {
    enum class Status {
        TEMP, COMPLETE
    }

    fun submit() {
        if (this.status != Status.TEMP) throw AlreadySubmittedReviewException("이미 제출된 리뷰입니다.")
        status = Status.COMPLETE
        submittedAt = LocalDateTime.now()
    }
}