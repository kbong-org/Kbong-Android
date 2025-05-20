package com.project.presentation.log

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.presentation.log.component.BottomActionBar
import com.project.presentation.log.component.GameLogObjectiveContent
import com.project.presentation.log.component.GameLogSubjectiveContent
import com.project.presentation.log.component.GameLogTextContent
import com.project.presentation.log.component.HeaderGameInfo
import com.project.presentation.log.component.TemplateTypeBottomSheet

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
    onSubmit: () -> Unit = {}
) {
    var inputType by remember { mutableStateOf(LogInputType.TEXT) }
    var selectedEmotion by remember { mutableStateOf(com.project.kbong.designsystem.R.drawable.emotion) }
    var isTemplateSheetVisible by remember { mutableStateOf(false) }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var text by remember { mutableStateOf("") }
    var selectedObjectiveOption by remember { mutableStateOf<String?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        if (imageUris.size < 4) {
            val updated = (imageUris + uris).distinct().take(4)
            imageUris = updated
        }
    }

    val canAddPhoto = imageUris.size < 4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KBongTopBar(
            isBackButton = true,
            onClickBackButton = { navController.popBackStack() },
            leftContent = {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(16.dp))
                    androidx.compose.material3.Text(text = gameInfo.awayTeamDisplayName)
                    Spacer(modifier = Modifier.width(8.dp))
                    androidx.compose.material3.Text(text = "vs")
                    Spacer(modifier = Modifier.width(8.dp))
                    androidx.compose.material3.Text(text = gameInfo.homeTeamDisplayName)
                }
            },
            rightContent = {
                androidx.compose.material3.Text("글 올리기", modifier = Modifier.clickable { onSubmit() })
            }
        )

        HeaderGameInfo(
            game = gameInfo,
            selectedDate = selectedDate,
            selectedEmotion = selectedEmotion,
            onEmotionSelected = { selectedEmotion = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                onSelectOption = { selectedObjectiveOption = it }
            )
            LogInputType.SUBJECTIVE -> GameLogSubjectiveContent(
                imageUris = imageUris,
                onAddImage = { imagePickerLauncher.launch("image/*") },
                onDeleteImage = { index -> imageUris = imageUris.toMutableList().apply { removeAt(index) } },
                canAdd = canAddPhoto,
                text = text,
                onTextChange = { text = it }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomActionBar(
            onPhotoClick = {
                if (canAddPhoto) imagePickerLauncher.launch("image/*")
            },
            onTemplateClick = { isTemplateSheetVisible = true },
            isPhotoAddEnabled = canAddPhoto
        )
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
}
