package com.project.data.mapper

import com.project.data.model.question.AnswerOptionDto
import com.project.data.model.question.ChoiceQuestionDto
import com.project.data.model.question.ShortQuestionDto
import com.project.domain.model.question.AnswerOption
import com.project.domain.model.question.ChoiceQuestion
import com.project.domain.model.question.ShortQuestion

fun ShortQuestionDto.toDomain() = ShortQuestion(questionId, questionText)

fun ChoiceQuestionDto.toDomain() = ChoiceQuestion(
    questionId = questionId,
    questionText = questionText,
    answerOptions = answerOptions.map { it.toDomain() }
)

fun AnswerOptionDto.toDomain() = AnswerOption(id, text)