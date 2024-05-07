package jjfactory.organization.review

interface PeerReviewService {
    fun create(loginUserId: Long, request: PeerReviewDto.Create): Long
    fun modify(loginUserId: Long, id: Long, request: PeerReviewDto.Update)
    fun submit(loginUserId: Long, id: Long)

    //리더거나 본인만 볼 수 있음.
    fun getOne(loginUserId: Long, id: Long)

    //내가 받은리뷰 리스트
    fun getReceiveReviewList(metaId: Long, userId: Long)

}