package jjfactory.organization.feedback

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
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

    @Test
    fun unAuthorizedThrow() {
        val feedbackId = 1L

        every { feedbackRepository.findByIdOrNull(1L) } returns
            Feedback(
                sendUserId = 3L,
                receiveUserId = 4L,
                content = ""
            )

        Assertions.assertThatThrownBy {
            feedbackService.update(
                loginUserId = 2L,
                feedbackId = feedbackId,
                request = FeedbackDto.Update(content = "수정~")
            )
        }.isInstanceOf(IllegalArgumentException::class.java)


    }

}