package com.langitbiru.idol.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.langitbiru.idol.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(onNavigateClick: (route: String) -> Unit ) {
    val viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {


      Button(
          onClick = { onNavigateClick(Screen.Kabesha.route) }
      ){
          Text("Kabesha")
      }


    }
}

