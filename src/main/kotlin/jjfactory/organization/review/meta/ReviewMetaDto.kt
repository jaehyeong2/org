package jjfactory.organization.review.meta

import jjfactory.organization.review.ReviewType
import java.time.LocalDate

class ReviewMetaDto {
    data class CreateRequest(
        val name: String,
        var startDt: LocalDate,
        var endDt: LocalDate,
        val reviewType: ReviewType,
    ){
        fun toEntity(): ReviewMeta {
            return ReviewMeta(
                name = name,
                startDt = startDt,
                endDt = endDt,
                reviewType = reviewType,
            )
        }
    }

    data class UpdateRequest(
        val name: String,
        var startDt: LocalDate,
        var endDt: LocalDate,
    )

    data class DetailResponse(
        val id: Long,
        val name: String,
        val startDt: LocalDate,
        val endDt: LocalDate,
        val reviewType: ReviewType,
    )

    data class ListResponse(
        val id: Long,
        val name: String,
        val startDt: LocalDate,
        val endDt: LocalDate,
        val reviewType: ReviewType,
    )
}