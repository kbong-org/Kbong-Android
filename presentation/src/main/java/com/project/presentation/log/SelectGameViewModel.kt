package com.project.presentation.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.model.day.DailyGameLog
import com.project.domain.usecase.log.GetGamesForLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SelectGameViewModel @Inject constructor(
    private val getGamesForLogUseCase: GetGamesForLogUseCase
) : ViewModel() {

    private val _gameList = MutableStateFlow<List<DailyGameLog>>(emptyList())
    val gameList: StateFlow<List<DailyGameLog>> = _gameList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun fetchGames(date: LocalDate) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            getGamesForLogUseCase(date)
                .onSuccess {
                    _gameList.value = it
                }.onFailure {
                    _errorMessage.value = it.message ?: "알 수 없는 오류"
                }

            _isLoading.value = false
        }
    }
}