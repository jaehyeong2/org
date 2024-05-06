package jjfactory.organization.feedback

interface FeedbackService {
    fun create(loginUserId: Long, request: FeedbackDto.Create): Long
    fun update(loginUserId: Long, id: Long, request: FeedbackDto.Update): Long
    fun getOne(id: Long): FeedbackDto.DetailResponse
    fun getListBySendUserId(id: Long): List<FeedbackDto.ListResponse>
    fun getListByReceiveUserId(id: Long): List<FeedbackDto.ListResponse>
}