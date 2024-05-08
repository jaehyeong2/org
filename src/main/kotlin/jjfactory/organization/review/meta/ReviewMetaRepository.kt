package jjfactory.organization.review.meta

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewMetaRepository: JpaRepository<ReviewMeta, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<ReviewMeta>
}