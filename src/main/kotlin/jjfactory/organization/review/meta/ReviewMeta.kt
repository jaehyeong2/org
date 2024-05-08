package jjfactory.organization.review.meta

import jakarta.persistence.*
import jjfactory.organization.review.ReviewType
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

    var isOpen: Boolean = false,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
) {
    fun open(){
        isOpen = true
    }

    fun close(){
        isOpen = false
    }
}