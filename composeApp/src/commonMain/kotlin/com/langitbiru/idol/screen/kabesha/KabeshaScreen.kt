package com.langitbiru.idol.screen.kabesha

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.langitbiru.idol.composable.StandardMemberCard
import com.langitbiru.idol.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun KabeshaScreen(onNavigateClick: (memberId:Int) -> Unit) {
    val viewModel: KabeshaViewModel = koinViewModel<KabeshaViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {


        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            itemsIndexed(uiState.members) {index, item ->
                StandardMemberCard(item){
                    onNavigateClick(index)
                }
            }
        }


    }
}