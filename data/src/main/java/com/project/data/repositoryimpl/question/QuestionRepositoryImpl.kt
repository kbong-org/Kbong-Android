package com.project.data.repositoryimpl.question

import com.project.data.service.QuestionService
import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.question.ShortQuestion
import com.project.domain.repository.question.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val service: QuestionService
) : QuestionRepository {

    override suspend fun getShortQuestion(): Result<ShortQuestion> = try {
        val response = service.getShortQuestion()
        if (response.isSuccess && response.data != null)
            Result.success(response.data.toDomain())
        else Result.failure(Exception(response.errorResponse.message))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getChoiceQuestions(): Result<List<ChoiceQuestion>> = try {
        val response = service.getChoiceQuestions()
        if (response.isSuccess && response.data != null)
            Result.success(response.data.items.map { it.toDomain() })
        else Result.failure(Exception(response.errorResponse.message))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getRefreshedChoiceQuestion(): Result<ChoiceQuestion> {
        return try {
            val response = service.getRefreshedChoiceQuestion()
            if (response.isSuccess && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.errorResponse.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}