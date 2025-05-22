package com.project.presentation.log.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.log.DailyLogDetail
import com.project.domain.repository.log.LogRepository
import com.project.domain.usecase.log.GetLogDetailUseCase
import com.project.kbong.designsystem.theme.KBongTeamBears
import com.project.kbong.designsystem.theme.KBongTeamEagles
import com.project.kbong.designsystem.theme.KBongTeamGiants
import com.project.kbong.designsystem.theme.KBongTeamHeroes
import com.project.kbong.designsystem.theme.KBongTeamKTSub
import com.project.kbong.designsystem.theme.KBongTeamLions
import com.project.kbong.designsystem.theme.KBongTeamNc
import com.project.kbong.designsystem.theme.KBongTeamSsg
import com.project.kbong.designsystem.theme.KBongTeamTigers
import com.project.kbong.designsystem.theme.KBongTeamTwins
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogDetailViewModel @Inject constructor(
    private val getLogDetailUseCase: GetLogDetailUseCase,
    private val logRepository: LogRepository
) : ViewModel() {

    var logDetail by mutableStateOf<DailyLogDetail?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var myTeamName by mutableStateOf<String?>(null)
        private set

    var myTeamColor by mutableStateOf<Color?>(null)
        private set

    fun fetchLogDetail(id: Long) {
        viewModelScope.launch {
            isLoading = true
            logDetail = getLogDetailUseCase(id)
            isLoading = false
        }
    }

    fun setMyTeam(name: String) {
        myTeamName = name
        myTeamColor = when (name) {
            "LG" -> KBongTeamTwins
            "두산" -> KBongTeamBears
            "한화" -> KBongTeamEagles
            "KIA" -> KBongTeamTigers
            "KT" -> KBongTeamKTSub
            "NC" -> KBongTeamNc
            "삼성" -> KBongTeamLions
            "SSG" -> KBongTeamSsg
            "롯데" -> KBongTeamGiants
            "키움" -> KBongTeamHeroes
            else -> null
        }
    }

    suspend fun deleteLog(id: Long): Result<Unit> {
        return logRepository.deleteLog(id)
    }
}