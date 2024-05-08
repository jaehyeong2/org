package jjfactory.organization.feedback

import jjfactory.organization.exception.AccessDeniedException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FeedbackServiceImpl(
    private val feedbackRepository: FeedbackRepository,
    private val feedbackCommentRepository: FeedbackCommentRepository,
    private val feedbackLikeRepository: FeedbackLikeRepository
) : FeedbackService {
    override fun create(loginUserId: Long, request: FeedbackDto.Create): Long {
        val initEntity = request.toEntity(loginUserId)
        return feedbackRepository.save(initEntity).id!!
    }

    override fun update(loginUserId: Long, feedbackId: Long, request: FeedbackDto.Update): Long {
        val feedback = feedbackRepository.findByIdOrNull(feedbackId) ?: throw NotFoundException()

        if (feedback.sendUserId != loginUserId) throw AccessDeniedException()

        feedback.modify(request.content)

        return feedback.id!!
    }

    @Transactional(readOnly = true)
    override fun getOne(id: Long): FeedbackDto.DetailResponse {
        val feedback = feedbackRepository.findByIdOrNull(id) ?: throw NotFoundException()

        return FeedbackDto.DetailResponse(
            id = feedback.id!!,
            sendUserId = feedback.sendUserId,
            receiveUserId = feedback.receiveUserId,
            content = feedback.content
        )
    }

    @Transactional(readOnly = true)
    override fun getListByReceiveUserId(receiveUserId: Long): List<FeedbackDto.ListResponse> {
        return feedbackRepository.findAllByReceiveUserIdOrderByCreatedAtDesc(receiveUserId).map {
            FeedbackDto.ListResponse(
                id = it.id!!,
                sendUserId = it.sendUserId,
                receiveUserId = it.receiveUserId,
                content = it.content
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getListBySendUserId(sendUserId: Long): List<FeedbackDto.ListResponse> {
        return feedbackRepository.findAllBySendUserIdOrderByCreatedAtDesc(sendUserId).map {
            FeedbackDto.ListResponse(
                id = it.id!!,
                sendUserId = it.sendUserId,
                receiveUserId = it.receiveUserId,
                content = it.content
            )
        }
    }

    override fun like(loginUserId: Long, feedbackId: Long): Long {
        val feedback = feedbackRepository.findByIdOrNull(feedbackId) ?: throw NotFoundException()

        if (feedback.sendUserId == loginUserId) throw IllegalArgumentException("본인이 작성한 피드백은 좋아요를 누를 수 없습니다.")

        if (feedbackLikeRepository.existsByFeedbackIdAndUserId(
                feedbackId = feedbackId,
                userId = loginUserId
            )) {
            throw IllegalArgumentException("이미 좋아요를 누른 피드백입니다.")
        }

        val initLike = FeedbackLike(
            userId = loginUserId,
            feedbackId = feedbackId
        )

        return feedbackLikeRepository.save(initLike).id!!
    }

    override fun dislike(loginUserId: Long, feedbackId: Long) {
        feedbackLikeRepository.findByFeedbackIdAndUserId(
            feedbackId = feedbackId,
            userId = loginUserId
        )?.let {
            feedbackLikeRepository.delete(it)
        }
    }
}