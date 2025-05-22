package com.project.data.mapper

import com.project.data.model.log.AnswerDto
import com.project.data.model.log.AnswerItemDto
import com.project.data.model.log.ChoiceLogRequestDto
import com.project.data.model.log.DailyLogDetailResponseDto
import com.project.data.model.log.DetailDto
import com.project.data.model.log.FreeLogRequestDto
import com.project.data.model.log.GameInfoDto
import com.project.data.model.log.ShortLogRequestDto
import com.project.domain.model.log.ChoiceLogRequest
import com.project.domain.model.log.DailyLogDetail
import com.project.domain.model.log.Detail
import com.project.domain.model.log.Emotion
import com.project.domain.model.log.FreeLogRequest
import com.project.domain.model.log.GameInfo
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

fun DailyLogDetailResponseDto.toDomain(): DailyLogDetail {
    return DailyLogDetail(
        id = id,
        emotion = emotion,
        type = type,
        gameInfo = gameInfo.toDomain(),
        imagePaths = imagePaths,
        detail = detail?.toDomain()
    )
}

fun GameInfoDto.toDomain(): GameInfo {
    return GameInfo(
        id = id,
        awayTeamDisplayName = awayTeamDisplayName,
        homeTeamDisplayName = homeTeamDisplayName,
        stadiumFullName = stadiumFullName,
        date = date
    )
}

fun DetailDto.toDomain(): Detail {
    return Detail(
        text = text,
        questionId = questionId,
        questionText = questionText,
        answer = answer,
        selectedOption = selectedOption,
        answers = answers?.map { it.toDomain() }
    )
}

fun AnswerDto.toDomain(): Detail.Answer {
    return Detail.Answer(
        questionId = questionId,
        questionText = questionText,
        answerOptionId = answerOptionId,
        answerOptionText = answerOptionText
    )
}
