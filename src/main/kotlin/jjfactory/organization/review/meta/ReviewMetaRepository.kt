package jjfactory.organization.review.meta

import jjfactory.organization.review.meta.ReviewMeta
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewMetaRepository: JpaRepository<ReviewMeta, Long> {
    fun findAllByOrderByCreateDtDesc(): List<ReviewMeta>
}