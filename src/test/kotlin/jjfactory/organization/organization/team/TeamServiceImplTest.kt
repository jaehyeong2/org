package jjfactory.organization.organization.team

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jjfactory.organization.user.Role
import jjfactory.organization.user.User
import jjfactory.organization.user.UserRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
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
    fun `어드민 아니면 삭제 시 exception`(){
        every { userRepository.findByIdOrNull(any()) } returns User(
            name = "lee",
            phone = "01012341234",
            email = "test@gmail.com",
            role = Role.NORMAL
        )

        assertThatThrownBy{
            teamService.deleteTeam(
                loginUserId = 2L,
                id = 2L
            )

        }.isInstanceOf(AccessDeniedException::class.java)
    }

    @Test
    fun `유저 등록된 팀은 삭제할 수 없다`(){
        val team = Team(
            id = 1L,
            name = "team",
            organizationId = 2L
        )
        every { teamRepository.findByIdOrNull(2L) } returns team

        every { userRepository.findByIdOrNull(any()) } returns User(
            name = "lee",
            phone = "01012341234",
            email = "test@gmail.com",
            role = Role.ADMIN
        )

        val teamUser = TeamUser(
            teamId = team.id!!,
            userId = 2L,
            position = "test",
        )

        every { teamUserRepository.findAllByTeamId(team.id!!) } returns
            listOf(teamUser)

        assertThatThrownBy{
            teamService.deleteTeam(
                loginUserId = 2L,
                id = 2L
            )

        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}