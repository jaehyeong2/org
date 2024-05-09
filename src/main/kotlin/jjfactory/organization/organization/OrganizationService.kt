package jjfactory.organization.organization

interface OrganizationService {
    fun create(request: OrganizationDto.Create): Long
    fun sendInviteMailToUser(request: OrganizationDto.InviteMailRequest)
    fun delete(id: Long)
}