package jjfactory.organization.feedback

import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackLikeRepository : JpaRepository<FeedbackLike, Long> {
    fun findAllByFeedbackIdOrderByCreatedAtDesc(feedbackId: Long): List<FeedbackLike>
    fun findAllByUserId(feedbackId: Long): List<FeedbackLike>
    fun existsByFeedbackIdAndUserId(feedbackId: Long, userId: Long): Boolean
    fun findByFeedbackIdAndUserId(feedbackId: Long, userId: Long): FeedbackLike?

}