package jjfactory.organization.review

import org.springframework.transaction.annotation.Transactional

@Transactional
class PeerReviewServiceImpl(
//    private val
) : PeerReviewService {
    override fun create(loginUserId: Long, request: PeerReviewDto.Create): Long {
        TODO("Not yet implemented")
    }

    override fun modify(loginUserId: Long, id: Long, request: PeerReviewDto.Update) {
        TODO("Not yet implemented")
    }

    override fun submit(loginUserId: Long, id: Long) {
        TODO("Not yet implemented")
    }

    override fun getOne(loginUserId: Long, id: Long) {
        TODO("Not yet implemented")
    }

    override fun getReceiveReviewList(metaId: Long, userId: Long) {
        TODO("Not yet implemented")
    }
}