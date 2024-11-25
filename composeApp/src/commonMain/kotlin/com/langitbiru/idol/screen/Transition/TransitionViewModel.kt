package com.langitbiru.idol.screen.Transition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.langitbiru.idol.model.Member
import com.langitbiru.idol.model.Members
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TransitionUiState(
    val name: String = "Elbuho",
    val message: String = "Hello World",
    val member: Member = Members.Eli
)


class TransitionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TransitionUiState())
    val uiState: StateFlow<TransitionUiState> = _uiState

    fun getMember(index: Int):Member = Members.All[index]
}