package jjfactory.organization.organization

import jjfactory.organization.user.Role

class OrganizationDto {
    data class Create(
        val name: String,
        val manager: ManagerContainer
    ){
        fun toEntity(): Organization {
            return Organization(
                name = name
            )
        }
    }

    data class ManagerContainer(
        val lastName: String,
        val firstName: String,
        val phone: String,
        val email: String
    )

    data class InviteMailRequest(
        val email: String,
        val role: Role
    )

    data class DetailResponse(
        val name: String
    )
}
