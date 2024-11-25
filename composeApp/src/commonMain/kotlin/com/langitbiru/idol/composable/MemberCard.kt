package com.langitbiru.idol.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.langitbiru.idol.model.Member
import com.seiko.imageloader.ui.AutoSizeImage

@Composable
fun StandardMemberCard(member: Member, onClick: () -> Unit = {}) {
    Column (
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick() }
    ) {
        AutoSizeImage(
            url = member.getKabeshaTransparentUrl(),
            contentDescription = "image",
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = member.nickname.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium

            )
        }
    }


}