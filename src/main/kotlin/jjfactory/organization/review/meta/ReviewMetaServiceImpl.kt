package jjfactory.organization.review.meta

import jjfactory.organization.exception.AccessDeniedException
import jjfactory.organization.review.ReviewType
import jjfactory.organization.user.Role
import jjfactory.organization.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ReviewMetaServiceImpl(
    private val reviewMetaRepository: ReviewMetaRepository,
    private val userRepository: UserRepository
) : ReviewMetaService {
    override fun createMeta(loginUserId: Long, request: ReviewMetaDto.CreateRequest): Long {
        validateAdminRole(loginUserId)
        return reviewMetaRepository.save(request.toEntity()).id!!
    }

    override fun updateMeta(loginUserId: Long, id: Long, request: ReviewMetaDto.UpdateRequest): Long {
        validateAdminRole(loginUserId)
        val meta = reviewMetaRepository.findByIdOrNull(id) ?: throw NotFoundException()
        if (meta.isOpen) throw IllegalArgumentException("open 상태 메타는 삭제/수정 불가능")

        meta.modify(
            name = request.name,
            startDt = request.startDt,
            endDt = request.endDt
        )

        return meta.id!!
    }

    override fun deleteMeta(loginUserId: Long, id: Long) {
        validateAdminRole(loginUserId)
        val meta = reviewMetaRepository.findByIdOrNull(id) ?: throw NotFoundException()

        if (meta.isOpen) throw IllegalArgumentException("open 상태 메타는 삭제/수정 불가능")
        reviewMetaRepository.deleteById(id)
    }

    override fun open(loginUserId: Long, metaId: Long){
        validateAdminRole(loginUserId)
        val meta = reviewMetaRepository.findByIdOrNull(metaId) ?: throw NotFoundException()
        meta.open()
    }

    override fun close(loginUserId: Long, metaId: Long){
        validateAdminRole(loginUserId)
        val meta = reviewMetaRepository.findByIdOrNull(metaId) ?: throw NotFoundException()
        meta.close()
    }

    @Transactional(readOnly = true)
    override fun getAllMetaList(type: ReviewType?): List<ReviewMetaDto.ListResponse> {
        //fixme 동적 쿼리
        type?.let {
            return reviewMetaRepository.findAllByReviewTypeOrderByCreatedAtDesc(it).map {
                ReviewMetaDto.ListResponse(
                    id = it.id!!,
                    name = it.name,
                    startDt = it.startDt,
                    endDt = it.endDt,
                    reviewType = it.reviewType
                )
            }
        }

        return  reviewMetaRepository.findAllByOrderByCreatedAtDesc().map {
            ReviewMetaDto.ListResponse(
                id = it.id!!,
                name = it.name,
                startDt = it.startDt,
                endDt = it.endDt,
                reviewType = it.reviewType
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getMeta(id: Long): ReviewMetaDto.DetailResponse {
        val meta = reviewMetaRepository.findByIdOrNull(id) ?: throw NotFoundException()

        return ReviewMetaDto.DetailResponse(
            id = meta.id!!,
            name = meta.name,
            startDt = meta.startDt,
            endDt = meta.endDt,
            reviewType = meta.reviewType
        )
    }

    private fun validateAdminRole(loginUserId: Long) {
        val loginUser = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()
        if (loginUser.role != Role.ADMIN) throw AccessDeniedException()
    }
}