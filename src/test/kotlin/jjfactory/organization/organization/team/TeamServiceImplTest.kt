package jjfactory.organization.organization.team

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jjfactory.organization.feedback.FeedbackDto
import jjfactory.organization.user.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull


@ExtendWith(MockKExtension::class)
class TeamServiceImplTest(

){
    @InjectMockKs
    private lateinit var teamService: TeamServiceImpl

    @MockK
    private lateinit var teamRepository: TeamRepository
    @MockK
    private lateinit var userRepository: UserRepository
    @MockK
    private lateinit var teamUserRepository: TeamUserRepository

    @Test
    fun `유저 등록된 팀은 삭제할 수 없다`(){
        val team = Team(
            id = 1L,
            name = "team",
            organizationId = 2L
        )
        every { teamRepository.findByIdOrNull(2L) } returns team

        val teamUser = TeamUser(
            teamId = team.id!!,
            userId = 2L,
            position = "test"
        )

        every { teamUserRepository.findAllByTeamId(team.id!!) } returns
            listOf(teamUser)

        Assertions.assertThatThrownBy{
            teamService.delete(2L)

        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}