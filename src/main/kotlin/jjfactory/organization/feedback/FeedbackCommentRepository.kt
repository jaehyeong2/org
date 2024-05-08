package jjfactory.organization.feedback

import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackCommentRepository : JpaRepository<FeedbackComment, Long> {
    fun findAllByFeedbackIdOrderByCreatedAtDesc(feedbackId: Long): List<FeedbackComment>
    fun findAllByUserId(feedbackId: Long): List<FeedbackComment>

}