package jjfactory.organization.external

import org.springframework.stereotype.Component

@Component
class FakeMailSender : MailSender {
    override fun sendManagerMail(token: String, email: String) {
        TODO("Not yet implemented")
    }

    override fun sendInviteMail(token: String, email: String) {
        TODO("Not yet implemented")
    }
}