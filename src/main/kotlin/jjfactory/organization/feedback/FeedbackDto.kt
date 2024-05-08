package jjfactory.organization.feedback

import java.time.LocalDateTime

class FeedbackDto {
    data class Create(
        val receiveUserId: Long,
        val content: String,
    ){
      fun toEntity(sendUserId: Long): Feedback {
          return Feedback(
              sendUserId = sendUserId,
              receiveUserId = receiveUserId,
              content = content
          )
      }
    }

    data class Update(
        val content: String,
    )

    data class DetailResponse(
        val id: Long,
        val sendUserId: Long,
        val receiveUserId: Long,
        val content: String,
    )

    data class ListResponse(
        val id: Long,
        val sendUserId: Long,
        val receiveUserId: Long,
        val content: String,
    )

}

class FeedbackCommentDto{
    data class Create(
        val content: String
    )

    data class Update(
        val content: String
    )

    data class DetailResponse(
        val id: Long,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}