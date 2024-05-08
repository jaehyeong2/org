package jjfactory.organization.review

import jjfactory.organization.exception.AlreadySubmittedReviewException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AnswerSheetTest{

    @Test
    fun `리뷰 제출 성공`(){
        val sheet = AnswerSheet(
            userId = 2L,
            targetUserId = 3L,
            metaId = 19L,
            type = ReviewType.SElF
        )

        sheet.submit()

        assertThat(sheet.status).isEqualTo(AnswerSheet.Status.COMPLETE)
        assertThat(sheet.submittedAt).isNotNull
    }

    @Test
    fun `temp상태 아니면 제출 시 throw`(){
        val sheet = AnswerSheet(
            userId = 2L,
            targetUserId = 3L,
            metaId = 19L,
            type = ReviewType.SElF,
            status = AnswerSheet.Status.COMPLETE
        )

        assertThatThrownBy {
            sheet.submit()
        }.isInstanceOf(AlreadySubmittedReviewException::class.java)
    }

}