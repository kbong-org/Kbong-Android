package com.project.presentation.log.write

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.model.log.Emotion
import com.project.domain.model.log.FreeLogRequest
import com.project.domain.model.log.ShortLogRequest
import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.question.ShortQuestion
import com.project.domain.repository.log.LogRepository
import com.project.domain.usecase.log.PostFreeLogUseCase
import com.project.domain.usecase.question.GetChoiceQuestionsUseCase
import com.project.domain.usecase.question.GetRefreshChoiceQuestionUseCase
import com.project.domain.usecase.question.GetShortQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameLogViewModel @Inject constructor(
    private val postFreeLogUseCase: PostFreeLogUseCase,
    private val logRepository: LogRepository,
    private val getShortQuestionUseCase: GetShortQuestionUseCase,
    private val getChoiceQuestionsUseCase: GetChoiceQuestionsUseCase,
    private val getRefreshChoiceQuestionUseCase: GetRefreshChoiceQuestionUseCase,
    private val postShortLogUseCase: com.project.domain.usecase.log.PostShortLogUseCase,
    private val postChoiceLogUseCase: com.project.domain.usecase.log.PostChoiceLogUseCase,
) : ViewModel() {

    fun uploadFreeLog(
        gameId: Long,
        text: String,
        emotion: Emotion,
        imagePaths: List<String>,
        onSuccess: () -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch {
            val result = postFreeLogUseCase(
                FreeLogRequest(
                    gameId = gameId,
                    text = text,
                    emotion = emotion,
                    imagePaths = imagePaths
                )
            )
            result.onSuccess {
                onSuccess() // 성공 콜백 호출
            }.onFailure {
                onFailure(it) // 실패 콜백 호출
            }
        }
    }

    fun uploadImagesAndSubmit(
        uris: List<Uri>,
        context: Context,
        gameId: Long,
        text: String,
        emotion: Emotion,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val paths = uris.map {
                    logRepository.uploadImageAndGetUrl(it, context)
                }
                postFreeLogUseCase(
                    FreeLogRequest(
                        gameId = gameId,
                        text = text,
                        emotion = emotion,
                        imagePaths = paths
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun uploadShortLog(
        gameId: Long,
        questionId: Long,
        text: String,
        emotion: Emotion,
        imageUris: List<Uri>,
        context: Context,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val imagePaths = imageUris.map {
                    logRepository.uploadImageAndGetUrl(it, context)
                }
                val result = postShortLogUseCase(
                    ShortLogRequest(
                        gameId = gameId,
                        questionId = questionId,
                        text = text,
                        emotion = emotion,
                        imagePaths = imagePaths
                    )
                )
                result.onSuccess { onSuccess() }.onFailure { onError(it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun uploadChoiceLog(
        gameId: Long,
        answerItems: List<ChoiceLogRequest.AnswerItem>,
        emotion: Emotion,
        imageUris: List<Uri>,
        context: Context,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val imagePaths = imageUris.map {
                    logRepository.uploadImageAndGetUrl(it, context)
                }
                val result = postChoiceLogUseCase(
                    ChoiceLogRequest(
                        gameId = gameId,
                        answerItems = answerItems,
                        emotion = emotion,
                        imagePaths = imagePaths
                    )
                )
                result.onSuccess { onSuccess() }.onFailure { onError(it) }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private val _shortQuestion = MutableStateFlow<ShortQuestion?>(null)
    val shortQuestion: StateFlow<ShortQuestion?> = _shortQuestion

    private val _choiceQuestions = MutableStateFlow<List<ChoiceQuestion>>(emptyList())
    val choiceQuestions: StateFlow<List<ChoiceQuestion>> = _choiceQuestions

    private val questionPageSize = 3

    fun loadShortQuestion() {
        viewModelScope.launch {
            getShortQuestionUseCase()
                .onSuccess { _shortQuestion.value = it }
                .onFailure { e -> println("ShortQuestion 에러: ${e.message}") }
        }
    }

    fun loadChoiceQuestions() {
        viewModelScope.launch {
            getChoiceQuestionsUseCase().onSuccess { response ->
                _choiceQuestions.value = response.take(questionPageSize)
            }
        }
    }

    private val _objectiveAnswers = mutableMapOf<Long, Long>()

    fun saveObjectiveAnswer(questionId: Long, answerId: Long) {
        _objectiveAnswers[questionId] = answerId
    }

    fun getObjectiveAnswer(questionId: Long): Long? {
        return _objectiveAnswers[questionId]
    }

    fun getAllSavedObjectiveAnswers(): List<ChoiceLogRequest.AnswerItem> {
        val validIds = _choiceQuestions.value.map { it.questionId }.toSet()

        return _objectiveAnswers
            .filterKeys { it in validIds } // 실제 질문만 포함
            .map { (questionId, answerId) ->
                ChoiceLogRequest.AnswerItem(questionId = questionId, answerOptionId = answerId)
            }
    }

    fun refreshChoiceQuestionAt(index: Int) {
        viewModelScope.launch {
            var newQuestion: ChoiceQuestion? = null
            val currentQuestions = _choiceQuestions.value
            val currentIds = currentQuestions.map { it.questionId }.toSet()

            // 기존 questionId 저장
            val oldQuestionId = currentQuestions.getOrNull(index)?.questionId

            repeat(5) { // 최대 5번 시도
                val result = getRefreshChoiceQuestionUseCase()
                val question = result.getOrNull()
                if (question != null && question.questionId !in currentIds) {
                    newQuestion = question
                    return@repeat
                }
            }

            newQuestion?.let { validQuestion ->
                _choiceQuestions.value = currentQuestions.toMutableList().apply {
                    if (index in indices) this[index] = validQuestion
                }

                // 이전 질문의 답변 삭제
                oldQuestionId?.let {
                    _objectiveAnswers.remove(it)
                }
            }
        }
    }
}