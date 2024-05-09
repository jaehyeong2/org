package jjfactory.organization.external

interface MailSender {
    fun sendManagerMail(token: String, email: String)
    fun sendInviteMail(email: String, role: String)
}