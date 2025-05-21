package com.project.data.mapper

import com.project.data.model.log.AnswerItemDto
import com.project.data.model.log.ChoiceLogRequestDto
import com.project.data.model.log.FreeLogRequestDto
import com.project.data.model.log.ShortLogRequestDto
import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.model.log.Emotion
import com.project.domain.model.log.FreeLogRequest
import com.project.domain.model.log.ShortLogRequest

fun FreeLogRequest.toDto(): FreeLogRequestDto {
    return FreeLogRequestDto(
        gameId = gameId,
        text = text,
        emotion = Emotion.valueOf(emotion.name),
        imagePaths = imagePaths
    )
}

fun ShortLogRequest.toDto(): ShortLogRequestDto {
    return ShortLogRequestDto(
        gameId = gameId,
        questionId = questionId,
        text = text,
        emotion = Emotion.valueOf(emotion.name),
        imagePaths = imagePaths
    )
}

fun ChoiceLogRequest.toDto(): ChoiceLogRequestDto {
    return ChoiceLogRequestDto(
        gameId = gameId,
        emotion = Emotion.valueOf(emotion.name),
        imagePaths = imagePaths,
        answerItems = answerItems.map { it.toDto() }
    )
}

fun ChoiceLogRequest.AnswerItem.toDto(): AnswerItemDto {
    return AnswerItemDto(
        questionId = questionId,
        answerOptionId = answerOptionId
    )
}