package jjfactory.organization.review

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ReviewMetaServiceImpl(
    private val reviewMetaRepository: ReviewMetaRepository
) : ReviewMetaService {
    override fun createMeta(request: ReviewMetaDto.CreateRequest): Long {
        return reviewMetaRepository.save(request.toEntity()).id!!
    }

    override fun updateMeta(id: Long, request: ReviewMetaDto.UpdateRequest): Long {
        val meta = reviewMetaRepository.findByIdOrNull(id) ?: throw NotFoundException()

        //fixme
        return meta.id!!
    }

    override fun deleteMeta(id: Long) {
        val meta = reviewMetaRepository.findByIdOrNull(id) ?: throw NotFoundException()
        reviewMetaRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun getAllMetaList() {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getMeta() {
        TODO("Not yet implemented")
    }
}