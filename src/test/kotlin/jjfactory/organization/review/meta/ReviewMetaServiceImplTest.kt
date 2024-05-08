package jjfactory.organization.review.meta

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import jjfactory.organization.review.ReviewType
import jjfactory.organization.user.Role
import jjfactory.organization.user.User
import jjfactory.organization.user.UserRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class ReviewMetaServiceImplTest{
    @InjectMockKs
    private lateinit var reviewMetaServiceImpl: ReviewMetaServiceImpl

    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var reviewMetaRepository: ReviewMetaRepository

    @Test
    fun `open 상태이면 수정 불가능`(){
        every { userRepository.findByIdOrNull(any()) } returns User(
            name = "lee",
            email = "wogud222@naver.com",
            phone = "01012341234",
            role = Role.ADMIN
        )

        val meta = ReviewMeta(
            id = 2L,
            name = "meta1",
            startDt = LocalDate.now().minusDays(7),
            endDt = LocalDate.now().minusDays(2),
            reviewType = ReviewType.PEER,
            isOpen = true
        )
        every { reviewMetaRepository.findByIdOrNull(any()) } returns meta

        val request = ReviewMetaDto.UpdateRequest(
            name = "update",
            startDt = LocalDate.now(),
            endDt = LocalDate.now()
        )

        assertThatThrownBy {
            reviewMetaServiceImpl.updateMeta(
                loginUserId = 2L,
                id = meta.id!!,
                request = request
            )
        }.isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun `open 상태이면 삭제 불가능`(){
        every { userRepository.findByIdOrNull(any()) } returns User(
            name = "lee",
            email = "wogud222@naver.com",
            phone = "01012341234",
            role = Role.ADMIN
        )

        val meta = ReviewMeta(
            id = 2L,
            name = "meta1",
            startDt = LocalDate.now().minusDays(7),
            endDt = LocalDate.now().minusDays(2),
            reviewType = ReviewType.PEER,
            isOpen = true
        )
        every { reviewMetaRepository.findByIdOrNull(any()) } returns meta

        assertThatThrownBy {
            reviewMetaServiceImpl.deleteMeta(
                loginUserId = 2L,
                id = meta.id!!
            )
        }.isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun `종료 일자보다 이후이면 open 불가능`(){
        every { userRepository.findByIdOrNull(any()) } returns User(
            name = "lee",
            email = "wogud222@naver.com",
            phone = "01012341234",
            role = Role.ADMIN
        )

        every { reviewMetaRepository.findByIdOrNull(any()) } returns ReviewMeta(
            name = "meta1",
            startDt = LocalDate.now().minusDays(7),
            endDt = LocalDate.now().minusDays(2),
            reviewType = ReviewType.PEER
        )

        assertThatThrownBy {
            reviewMetaServiceImpl.open(
                loginUserId = 2L,
                metaId = 17L
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}