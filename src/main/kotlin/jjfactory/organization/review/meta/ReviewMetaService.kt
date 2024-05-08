package jjfactory.organization.review.meta

import jjfactory.organization.review.meta.ReviewMetaDto

interface ReviewMetaService {
    fun getAllMetaList()
    fun getMeta()
    fun open(loginUserId: Long, metaId: Long)
    fun close(loginUserId: Long, metaId: Long)
    fun deleteMeta(loginUserId: Long, id: Long)
    fun updateMeta(loginUserId: Long, id: Long, request: ReviewMetaDto.UpdateRequest): Long
    fun createMeta(loginUserId: Long, request: ReviewMetaDto.CreateRequest): Long
}