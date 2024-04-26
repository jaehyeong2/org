package jjfactory.organization.review

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class ReviewMeta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    var startDt: LocalDate,
    var endDt: LocalDate,

    @Enumerated(EnumType.STRING)
    val reviewType: ReviewType,

    @CreationTimestamp
    val createDt: LocalDateTime? = null,
    @UpdateTimestamp
    val updateDt: LocalDateTime? = null,
) {
}