package com.project.presentation.log

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.project.domain.model.day.DailyGameLog
import com.project.kbong.designsystem.navigationbar.KBongTopBar
import com.project.kbong.designsystem.theme.KBongGrayscaleGray1
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongTypography
import com.project.presentation.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = gameInfo.awayTeamDisplayName, style = KBongTypography.Heading2)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "vs", style = KBongTypography.Heading2, color = Color(0xFF3D5AFE))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = gameInfo.homeTeamDisplayName, style = KBongTypography.Heading2)
                }
            },
            rightContent = {
                Text("글 올리기", modifier = Modifier.clickable { onSubmit() }, style = KBongTypography.Body1NormalSemiBold)
            }
        )

        HeaderGameInfo(
            game = gameInfo,
            selectedDate = selectedDate,
            selectedEmotion = selectedEmotion,
            onEmotionSelected = { selectedEmotion = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        var text by remember { mutableStateOf("") }

        when (inputType) {
            LogInputType.TEXT -> GameLogTextContent(
                imageUris = imageUris,
                onAddImage = { imagePickerLauncher.launch("image/*") },
                onDeleteImage = { index -> imageUris = imageUris.toMutableList().apply { removeAt(index) } },
                canAdd = imageUris.size < 4,
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
                canAdd = imageUris.size < 4,
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

@Composable
fun BottomActionBar(
    onPhotoClick: () -> Unit,
    onTemplateClick: () -> Unit,
    isPhotoAddEnabled: Boolean
) {
    Column {
        Divider(
            thickness = 2.dp,
            color = KBongGrayscaleGray1,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 사진 추가 버튼
            Image(
                painter = painterResource(id = R.drawable.add_photo),
                contentDescription = "사진 추가",
                modifier = Modifier
                    .clickable(enabled = isPhotoAddEnabled) { onPhotoClick() }
                    .size(24.dp)
            )

            // 템플릿 변경 버튼
            Image(
                painter = painterResource(id = R.drawable.change_template),
                contentDescription = "템플릿 변경",
                modifier = Modifier
                    .clickable { onTemplateClick() }
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun HeaderGameInfo(
    game: DailyGameLog,
    selectedDate: LocalDate,
    selectedEmotion: Int,
    onEmotionSelected: (Int) -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
        ) {
            Image(
                painter = painterResource(id = selectedEmotion),
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clickable { isMenuExpanded = true }
            )

            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf(
                        com.project.kbong.designsystem.R.drawable.win,
                        com.project.kbong.designsystem.R.drawable.cloud,
                        com.project.kbong.designsystem.R.drawable.lose,
                        com.project.kbong.designsystem.R.drawable.lightning
                    ).forEach { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clickable {
                                    onEmotionSelected(resId)
                                    isMenuExpanded = false
                                }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "시간"
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 " +
                            selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN),
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "장소",
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = game.stadiumDisplayName,
                    style = KBongTypography.Label2Medium,
                    color = Color.Gray
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Divider(
        thickness = 6.dp,
        color = KBongGrayscaleGray1,
    )

    Spacer(modifier = Modifier.height(8.dp))

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateTypeBottomSheet(
    selectedType: LogInputType,
    onSelect: (LogInputType) -> Unit,
    onDismiss: () -> Unit
) {
    val iconMapSelected = mapOf(
        LogInputType.TEXT to R.drawable.select_free,
        LogInputType.OBJECTIVE to R.drawable.select_objective,
        LogInputType.SUBJECTIVE to R.drawable.select_subjective
    )
    val iconMapUnselected = mapOf(
        LogInputType.TEXT to R.drawable.unselect_free,
        LogInputType.OBJECTIVE to R.drawable.unselect_objective,
        LogInputType.SUBJECTIVE to R.drawable.unselect_subjective
    )

    var tempSelectedType by remember { mutableStateOf(selectedType) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.navigationBarsPadding(), // 시스템 바 위까지 올라오게
        windowInsets = WindowInsets(0), // 시스템 inset 무시해서 전체 시트로
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        dragHandle = null, // 핸들 없애서 겹쳐 보이는 현상 제거
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Text(
                text = "어떤 형식으로 기록할까요?",
                style = KBongTypography.Heading2
            )

            Spacer(modifier = Modifier.height(20.dp))

            LogInputType.entries.forEach { type ->
                val isSelected = type == tempSelectedType
                val backgroundColor = if (isSelected) Color(0xFFF3F6FF) else Color(0xFFF9F9FA)
                val borderColor = if (isSelected) Color(0xFF3D5AFE) else Color.Transparent
                val iconRes = if (isSelected) iconMapSelected[type]!! else iconMapUnselected[type]!!

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .border(
                            width = if (isSelected) 1.dp else 0.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { tempSelectedType = type }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = type.title,
                            style = KBongTypography.Body1NormalSemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = type.description,
                            style = KBongTypography.Label2Medium,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    onSelect(tempSelectedType)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D5AFE))
            ) {
                Text("완료", color = Color.White)
            }
        }
    }
}

@Composable
fun GameLogTextContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        // 이미지 리스트
        if (imageUris.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(imageUris.size) { index ->
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(imageUris[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop, // 네모에 꽉 차게
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.photo_delete),
                            contentDescription = "삭제",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(20.dp)
                                .clickable { onDeleteImage(index) },
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 이미지가 하나도 없을 때만 버튼 노출
        if (imageUris.isEmpty() && canAdd) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { onAddImage() }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Add, contentDescription = "사진 추가", tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 텍스트 입력 (보더 없음, 플레이스홀더 조건부 노출)
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 300) onTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textStyle = KBongTypography.Body1NormalSemiBold.copy(color = Color.Black)
            )

            if (text.isEmpty()) {
                Text(
                    text = "오늘 경기에 대해 자유롭게 작성해보세요!",
                    style = KBongTypography.Body1NormalSemiBold,
                    color = KBongGrayscaleGray4,
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 글자 수 카운트 (좌측 정렬)
        if (text.isNotBlank()) {
            Text(
                text = "${text.length}/300",
                style = KBongTypography.Label2Medium,
                color = KBongGrayscaleGray4,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}

@Composable
fun GameLogObjectiveContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    selectedOption: String?,
    onSelectOption: (String) -> Unit,
    totalPages: Int = 3,
    onPageChange: (Int) -> Unit = {}
) {
    var currentPage by remember { mutableStateOf(1) }

    val options = listOf(
        "다시 보고 싶을 정도로 재미있었어요",
        "만족스러웠어요",
        "무난했어요",
        "별로였어요"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9FA), RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // 이미지 프리뷰
        if (imageUris.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(imageUris.size) { index ->
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(imageUris[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.photo_delete),
                            contentDescription = "삭제",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(20.dp)
                                .clickable { onDeleteImage(index) },
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 페이지 / 질문 변경
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("$currentPage/$totalPages", style = KBongTypography.Label2Medium, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "질문 바꾸기",
                style = KBongTypography.Label2Medium,
                color = Color(0xFF6B7AFF),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFF2FF))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "전체적인 경기 직관 소감은 어떤가요?",
            style = KBongTypography.Heading2
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 객관식 선택지
        options.forEach { option ->
            val isSelected = selectedOption == option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Color(0xFFEFF2FF) else Color.White)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color(0xFF6B7AFF) else Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onSelectOption(option) }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    option,
                    style = KBongTypography.Body2Reading,
                    modifier = Modifier.weight(1f)
                )
                if (isSelected) {
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "선택됨",
                        tint = Color(0xFF6B7AFF),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ⬅️ 이전 / 다음 ➡️ 버튼
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val canGoBack = currentPage > 1
            val canGoNext = currentPage < totalPages

            Image(
                painter = painterResource(
                    id = if (canGoBack) R.drawable.select_before else R.drawable.unselect_before
                ),
                contentDescription = "이전",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(enabled = canGoBack) {
                        currentPage--
                        onPageChange(currentPage)
                    }
            )

            Image(
                painter = painterResource(
                    id = if (canGoNext) R.drawable.select_after else R.drawable.unselect_after
                ),
                contentDescription = "다음",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(enabled = canGoNext) {
                        currentPage++
                        onPageChange(currentPage)
                    }
            )
        }
    }
}

@Composable
fun GameLogSubjectiveContent(
    imageUris: List<Uri>,
    onAddImage: () -> Unit,
    onDeleteImage: (Int) -> Unit,
    canAdd: Boolean,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(
            "오늘 경기를 보면서 '야, 야구장에서만 느낄 수 있는 기분이다!' 했던 순간이 있다면?",
            style = KBongTypography.Heading2
        )
        Spacer(modifier = Modifier.height(24.dp))

        Divider(
            thickness = 2.dp,
            color = KBongGrayscaleGray1,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 이미지 프리뷰
        if (imageUris.isNotEmpty()) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(imageUris.size) { index ->
                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(imageUris[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.photo_delete),
                            contentDescription = "삭제",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(20.dp)
                                .clickable { onDeleteImage(index) },
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 사진 추가 버튼 (이미지 없을 때만 표시)
        if (imageUris.isEmpty() && canAdd) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { onAddImage() },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "사진 추가", tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 텍스트 입력 필드 (보더 없음, 플레이스홀더 조건부 표시)
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 100) onTextChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textStyle = KBongTypography.Body2Normal.copy(color = Color.Black)
            )

            if (text.isEmpty()) {
                Text(
                    text = "질문에 답하기",
                    style = KBongTypography.Body2Normal,
                    color = KBongGrayscaleGray4,
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (text.isNotBlank()) {
            Text(
                text = "${text.length}/100",
                style = KBongTypography.Label2Medium,
                color = KBongGrayscaleGray4,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}

@Preview
@Composable
fun PreviewGameLogWriteScreen() {
    GameLogWriteRoute(
        navController = rememberNavController(),
        gameInfo = DailyGameLog(
            id = 1L,
            startTimeStr = "18:30",
            awayTeamDisplayName = "KT",
            homeTeamDisplayName = "한화",
            stadiumDisplayName = "수원"
        ),
        selectedDate = LocalDate.of(2025, 5, 15)
    )
}
