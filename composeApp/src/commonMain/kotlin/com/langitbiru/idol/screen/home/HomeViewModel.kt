package com.langitbiru.idol.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.langitbiru.idol.model.Member
import com.langitbiru.idol.model.Members
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val name: String = "Elbuho",
    val message: String = "Hello World",
    val members: List<Member> = Members.All
)


class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun updateName(newName: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(name = newName)
            }
        }
    }

    fun updateMessage(newMessage: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(message = newMessage)
            }
        }
    }
}
