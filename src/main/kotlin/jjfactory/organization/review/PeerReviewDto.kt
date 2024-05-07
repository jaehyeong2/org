package jjfactory.organization.review

class PeerReviewDto {
    data class Create(
        val content: String,
        val targetUserId: Long
    )

    data class Update(
        val content: String
    )

    data class Response(
        val id: Long,
        val content: String
    )
}