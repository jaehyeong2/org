package jjfactory.organization.feedback

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jjfactory.organization.exception.AccessDeniedException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
class FeedbackServiceImplTest(

) {
    @InjectMockKs
    private lateinit var feedbackService: FeedbackServiceImpl

    @MockK
    private lateinit var feedbackRepository: FeedbackRepository
    @MockK
    private lateinit var feedbackCommentRepository: FeedbackCommentRepository
    @MockK
    private lateinit var feedbackLikeRepository: FeedbackLikeRepository

    @Test
    fun `권한 없으면 수정 불가`() {
        val feedbackId = 1L

        every { feedbackRepository.findByIdOrNull(1L) } returns
            Feedback(
                sendUserId = 3L,
                receiveUserId = 4L,
                content = ""
            )

        assertThatThrownBy {
            feedbackService.update(
                loginUserId = 2L,
                feedbackId = feedbackId,
                request = FeedbackDto.Update(content = "수정~")
            )
        }.isInstanceOf(AccessDeniedException::class.java)
    }


    @Test
    fun `본인이 작성한 피드백 좋아요 불가`() {
        val feedbackId = 1L

        every { feedbackRepository.findByIdOrNull(1L) } returns
            Feedback(
                sendUserId = 3L,
                receiveUserId = 4L,
                content = ""
            )

        assertThatThrownBy {
            feedbackService.like(
                loginUserId = 3L,
                feedbackId = feedbackId,
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}