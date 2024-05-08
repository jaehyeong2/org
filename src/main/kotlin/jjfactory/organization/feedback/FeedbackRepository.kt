package jjfactory.organization.feedback

import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackRepository: JpaRepository<Feedback, Long> {
    fun findAllBySendUserIdOrderByCreatedAtDesc(sendUserId: Long): List<Feedback>
    fun findAllByReceiveUserIdOrderByCreatedAtDesc(sendUserId: Long): List<Feedback>
}