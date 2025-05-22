package com.project.presentation.log.write

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.domain.model.day.DailyGameLog
import com.project.domain.model.log.Emotion
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.log.Dialog.CancelWritingDialog
import com.project.presentation.log.write.component.BottomActionBar
import com.project.presentation.log.component.GameLogObjectiveContent
import com.project.presentation.log.component.GameLogSubjectiveContent
import com.project.presentation.log.component.GameLogTextContent
import com.project.presentation.log.write.component.HeaderGameInfo
import com.project.presentation.log.write.component.TemplateTypeBottomSheet
import java.time.LocalDate

enum class LogInputType(val title: String, val description: String) {
    TEXT("자율형", "정해진 양식 없이 자유롭게 기록해요"),
    OBJECTIVE("객관형", "체크 형식으로 간단히 기록해요"),
    SUBJECTIVE("주관형", "간단한 한줄 작성으로 기록해요")
}

@Composable
fun GameLogWriteRoute(
    navController: NavController,
    gameInfo: DailyGameLog,
    selectedDate: LocalDate,
    viewModel: GameLogViewModel = hiltViewModel(),
    onSubmit: () -> Unit = {}
) {
    var inputType by remember { mutableStateOf(LogInputType.TEXT) }
    var selectedEmotion by remember { mutableStateOf(com.project.kbong.designsystem.R.drawable.emotion) }
    var isTemplateSheetVisible by remember { mutableStateOf(false) }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var text by remember { mutableStateOf("") }

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        val availableSlots = 4 - imageUris.size
        if (uris.size > availableSlots) {
            Toast
                .makeText(context, "이미지는 최대 4장까지 선택할 수 있어요.", Toast.LENGTH_SHORT)
                .show()
        }

        if (availableSlots > 0) {
            val limitedUris = uris.take(availableSlots)
            imageUris = (imageUris + limitedUris).distinct()
        }
    }

    val canAddPhoto = imageUris.size < 4

    val shortQuestion by viewModel.shortQuestion.collectAsState()
    val choiceQuestions by viewModel.choiceQuestions.collectAsState()
    val choiceQuestion = choiceQuestions.getOrNull(0)

    var currentObjectivePage by remember { mutableStateOf(0) }
    val currentObjectiveQuestion = choiceQuestions.getOrNull(currentObjectivePage)
    var selectedObjectiveOption by remember {
        mutableStateOf(
            currentObjectiveQuestion?.answerOptions
                ?.firstOrNull { it.id == viewModel.getObjectiveAnswer(
                    currentObjectiveQuestion.questionId
                ) }
                ?.text
        )
    }

    var showCancelDialog by remember { mutableStateOf(false) }

    // 시스템 뒤로가기 핸들링
    BackHandler {
        showCancelDialog = true
    }

    LaunchedEffect(inputType) {
        when (inputType) {
            LogInputType.SUBJECTIVE -> viewModel.loadShortQuestion()
            LogInputType.OBJECTIVE -> viewModel.loadChoiceQuestions()
            else -> {}
        }
    }

    // 감정 이모지 -> Emotion Enum 매핑 함수
    fun mapEmotion(drawableId: Int): Emotion {
        return when (drawableId) {
            com.project.kbong.designsystem.R.drawable.win -> Emotion.HAPPY
            com.project.kbong.designsystem.R.drawable.cloud -> Emotion.NORMAL
            com.project.kbong.designsystem.R.drawable.lose -> Emotion.SAD
            com.project.kbong.designsystem.R.drawable.lightning -> Emotion.ANGRY
            else -> Emotion.NORMAL
        }
    }

    // 제출 핸들링
    val handleSubmit = {
        when (inputType) {
            LogInputType.TEXT -> {
                viewModel.uploadImagesAndSubmit(
                    uris = imageUris,
                    context = context,
                    gameId = gameInfo.id,
                    text = text,
                    emotion = mapEmotion(selectedEmotion),
                    onSuccess = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("shouldRefreshLog", true)
                        navController.popBackStack(route = "calendar", inclusive = false)
                    },
                    onError = {
                        println("자유형 업로드 실패: ${it.message}")
                    }
                )
            }

            LogInputType.SUBJECTIVE -> {
                val questionId = viewModel.shortQuestion.value?.questionId
                if (questionId != null) {
                    viewModel.uploadShortLog(
                        gameId = gameInfo.id,
                        questionId = questionId,
                        text = text,
                        emotion = mapEmotion(selectedEmotion),
                        imageUris = imageUris,
                        context = context,
                        onSuccess = {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("shouldRefreshLog", true)
                            navController.popBackStack(route = "calendar", inclusive = false)
                        },
                        onError = {
                            println("주관형 업로드 실패: ${it.message}")
                        }
                    )
                } else {
                    println("주관형 질문이 null입니다.")
                }
            }

            LogInputType.OBJECTIVE -> {
                val answerItems = viewModel.getAllSavedObjectiveAnswers()
                if (answerItems.isNotEmpty()) {
                    viewModel.uploadChoiceLog(
                        gameId = gameInfo.id,
                        answerItems = answerItems,
                        emotion = mapEmotion(selectedEmotion),
                        imageUris = imageUris,
                        context = context,
                        onSuccess = {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("shouldRefreshLog", true)
                            navController.popBackStack(route = "calendar", inclusive = false)
                        },
                        onError = {
                            println("객관형 업로드 실패: ${it.message}")
                        }
                    )
                } else {
                    println("객관형 질문 또는 선택된 답변이 없습니다.")
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 스크롤 가능한 콘텐츠 (패딩 확보)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 58.dp) // BottomActionBar 높이만큼 패딩
        ) {
            item {
                KBongTopBar(
                    isBackButton = true,
                    onClickBackButton = { showCancelDialog = true },
                    leftContent = {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = KBongTypography.Heading2.toSpanStyle()) {
                                    append(gameInfo.awayTeamDisplayName)
                                    append(" ")
                                }
                                withStyle(
                                    style = KBongTypography.Heading2.toSpanStyle().copy(color = KBongPrimary)
                                ) {
                                    append("vs")
                                }
                                withStyle(style = KBongTypography.Heading2.toSpanStyle()) {
                                    append(" ${gameInfo.homeTeamDisplayName}")
                                }
                            }
                        )
                    },
                    rightContent = {
                        Text(
                            "글 올리기",
                            modifier = Modifier.clickable { handleSubmit() }
                        )
                    }
                )
            }

            item {
                HeaderGameInfo(
                    game = gameInfo,
                    selectedDate = selectedDate,
                    selectedEmotion = selectedEmotion,
                    onEmotionSelected = { selectedEmotion = it }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                when (inputType) {
                    LogInputType.TEXT -> GameLogTextContent(
                        imageUris = imageUris,
                        onAddImage = { imagePickerLauncher.launch("image/*") },
                        onDeleteImage = { index -> imageUris = imageUris.toMutableList().apply { removeAt(index) } },
                        canAdd = canAddPhoto,
                        text = text,
                        onTextChange = { text = it }
                    )

                    LogInputType.OBJECTIVE -> GameLogObjectiveContent(
                        imageUris = imageUris,
                        onAddImage = { imagePickerLauncher.launch("image/*") },
                        onDeleteImage = { index -> imageUris = imageUris.toMutableList().apply { removeAt(index) } },
                        canAdd = canAddPhoto,
                        selectedOption = selectedObjectiveOption,
                        onSelectOption = {
                            selectedObjectiveOption = it
                            val question = currentObjectiveQuestion
                            val answerId = question?.answerOptions?.firstOrNull { opt -> opt.text == it }?.id
                            if (question != null && answerId != null) {
                                viewModel.saveObjectiveAnswer(question.questionId, answerId)
                            }
                        },
                        question = currentObjectiveQuestion,
                        currentPage = currentObjectivePage + 1,
                        onPageChange = {
                            currentObjectivePage = it - 1
                            val question = choiceQuestions.getOrNull(currentObjectivePage)
                            val savedAnswerId = question?.questionId?.let(viewModel::getObjectiveAnswer)
                            selectedObjectiveOption = question?.answerOptions
                                ?.firstOrNull { opt -> opt.id == savedAnswerId }
                                ?.text
                        },
                        onRefreshQuestion = {
                            viewModel.refreshChoiceQuestionAt(currentObjectivePage)
                        }
                    )

                    LogInputType.SUBJECTIVE -> GameLogSubjectiveContent(
                        imageUris = imageUris,
                        onAddImage = { imagePickerLauncher.launch("image/*") },
                        onDeleteImage = { index -> imageUris = imageUris.toMutableList().apply { removeAt(index) } },
                        canAdd = canAddPhoto,
                        text = text,
                        onTextChange = { text = it },
                        question = shortQuestion,
                        onRefreshQuestion = { viewModel.loadShortQuestion() }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp)) // 끝 여백 추가
            }
        }

        // 하단 고정 바텀바
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            BottomActionBar(
                onPhotoClick = {
                    if (canAddPhoto) imagePickerLauncher.launch("image/*")
                },
                onTemplateClick = { isTemplateSheetVisible = true },
                isPhotoAddEnabled = canAddPhoto
            )
        }
    }

    if (isTemplateSheetVisible) {
        TemplateTypeBottomSheet(
            selectedType = inputType,
            onSelect = {
                inputType = it
                isTemplateSheetVisible = false
            },
            onDismiss = { isTemplateSheetVisible = false }
        )
    }

    if (showCancelDialog) {
        CancelWritingDialog(
            onConfirm = {
                showCancelDialog = false
                navController.popBackStack()
            },
            onDismiss = {
                showCancelDialog = false
            }
        )
    }
}