package jjfactory.organization.review

interface ReviewMetaService {
    fun createMeta(request: ReviewMetaDto.CreateRequest): Long
    fun updateMeta(id: Long, request: ReviewMetaDto.UpdateRequest): Long
    fun getAllMetaList()
    fun getMeta()
    fun deleteMeta(id: Long)
}