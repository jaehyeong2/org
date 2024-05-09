package jjfactory.organization.organization.team

class TeamDto {
    data class CreateRequest(
        val name: String,
        val parentId: Long,
        val leaderId: Long,
    ){
        fun toEntity(organizationId: Long): Team {
            return Team(
                leaderId = leaderId,
                name = name,
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
        val id: Long
    )
}