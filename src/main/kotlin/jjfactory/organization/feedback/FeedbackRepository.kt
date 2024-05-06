package jjfactory.organization.feedback

import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackRepository: JpaRepository<Feedback, Long> {
    fun findAllBySendUserIdOrderByCreateDtDesc(sendUserId: Long): List<Feedback>
    fun findAllByReceiveUserIdOrderByCreateDtDesc(sendUserId: Long): List<Feedback>
}