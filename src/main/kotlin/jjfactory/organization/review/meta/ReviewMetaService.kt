package jjfactory.organization.review.meta

import jjfactory.organization.review.meta.ReviewMetaDto

interface ReviewMetaService {
    fun createMeta(request: ReviewMetaDto.CreateRequest): Long
    fun updateMeta(id: Long, request: ReviewMetaDto.UpdateRequest): Long
    fun getAllMetaList()
    fun getMeta()
    fun deleteMeta(id: Long)
    fun open(loginUserId: Long, metaId: Long)
    fun close(loginUserId: Long, metaId: Long)
}