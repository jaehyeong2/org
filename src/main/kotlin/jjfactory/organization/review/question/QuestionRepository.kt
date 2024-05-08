package jjfactory.organization.review.question

import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<Question, Long> {
    fun findAllByMetaIdOrderByIdx(metaId: Long): List<Question>
}