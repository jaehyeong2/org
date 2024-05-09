package jjfactory.organization.organization

interface OrganizationService {
    fun create(request: OrganizationDto.Create): Long
    fun delete(id: Long)
    fun sendInviteMailToUser(loginUserId: Long, request: OrganizationDto.InviteMailRequest)
}