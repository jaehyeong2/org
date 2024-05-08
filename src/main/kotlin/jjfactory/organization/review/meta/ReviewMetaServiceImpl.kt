package jjfactory.organization.review.meta

import jjfactory.organization.exception.AccessDeniedException
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

        //fixme
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
    override fun getAllMetaList() {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getMeta() {
        TODO("Not yet implemented")
    }

    private fun validateAdminRole(loginUserId: Long) {
        val loginUser = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()
        if (loginUser.role != Role.ADMIN) throw AccessDeniedException()
    }
}