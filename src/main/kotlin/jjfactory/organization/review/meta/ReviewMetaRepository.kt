package jjfactory.organization.review.meta

import jjfactory.organization.review.ReviewType
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewMetaRepository: JpaRepository<ReviewMeta, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<ReviewMeta>
    fun findAllByReviewTypeOrderByCreatedAtDesc(type: ReviewType): List<ReviewMeta>
}