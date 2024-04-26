package jjfactory.organization.review

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewMetaRepository: JpaRepository<ReviewMeta, Long> {
    fun findAllByOrderByCreateDtDesc(): List<ReviewMeta>
}