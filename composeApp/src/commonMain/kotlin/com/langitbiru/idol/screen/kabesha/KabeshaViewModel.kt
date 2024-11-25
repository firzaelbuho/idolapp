package com.langitbiru.idol.screen.kabesha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.langitbiru.idol.model.Member
import com.langitbiru.idol.model.Members
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class KabeshaUiState(
    val name: String = "Elbuho",
    val message: String = "Hello World",
    val members: List<Member> = Members.All
)


class KabeshaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(KabeshaUiState())
    val uiState: StateFlow<KabeshaUiState> = _uiState

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