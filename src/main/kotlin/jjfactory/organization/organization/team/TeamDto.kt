package jjfactory.organization.organization.team

class TeamDto {
    data class CreateRequest(
        val name: String,
        val parentId: Long?,
        val leaderId: Long,
    ){
        fun toEntity(organizationId: Long): Team {
            return Team(
                leaderId = leaderId,
                name = name,
                parentId = parentId,
                organizationId = organizationId,
            )
        }
    }

    data class UpdateRequest(
        val name: String,
    )

    data class AddUserRequest(
        val userId: Long,
        val position: String
    )

    data class ListResponse(
        val id: Long,
        val name: String,
        val parentId: Long?,
    )

    data class DetailResponse(
        val id: Long,
        val name: String,
        val users: List<UserContainer> = ArrayList()
    )

    data class UserContainer(
        val id: Long,
        val name: String
    )
}