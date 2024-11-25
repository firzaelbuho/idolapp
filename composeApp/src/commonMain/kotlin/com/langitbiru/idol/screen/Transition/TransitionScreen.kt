package com.langitbiru.idol.screen.Transition

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.langitbiru.idol.composable.HlsmAnimation
import com.langitbiru.idol.model.Member
import com.seiko.imageloader.ui.AutoSizeImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransitionScreen(memberId: Int, onBackClick: () -> Unit = {}){
    val viewModel: TransitionViewModel = koinViewModel<TransitionViewModel>()
    val member = viewModel.getMember(memberId)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onBackClick() },
    ) {


        HlsmAnimation.ZoomInFadeIn(initialScale = 0.1f, durationMillis = 3000){ ImageSection(member.getKabeshaTransparentUrl()) }
        HlsmAnimation.SlideIn(delayMillis = 1000) {  TextSection(member.nickname!!) }


    }
}

@Composable
fun ImageSection(url:String){
    AutoSizeImage(
        modifier = Modifier.fillMaxSize(),
        url = url,
        contentDescription = "image",
        contentScale = ContentScale.FillHeight
    )
}

@Composable
fun TextSection(name:String){
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            text = name,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}