package com.langitbiru.idol.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SlideDirection {
    Left, Right, Up, Down
}



object HlsmAnimation{
    private val slideOffset = 300f
    @Composable
    fun SlideIn(
        durationMillis: Int = 1000,
        delayMillis: Int = 0, // Parameter waktu delay
        slideDirection: SlideDirection = SlideDirection.Up,
        content: @Composable () -> Unit
    ) {
        // Tentukan nilai awal berdasarkan arah
        val initialOffset = when (slideDirection) {
            SlideDirection.Left -> -slideOffset
            SlideDirection.Right -> slideOffset
            SlideDirection.Up -> -slideOffset
            SlideDirection.Down -> slideOffset
        }

        val offsetX = remember { Animatable(if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) initialOffset else 0f) }
        val offsetY = remember { Animatable(if (slideDirection == SlideDirection.Up || slideDirection == SlideDirection.Down) initialOffset else 0f) }

        // Animasi slide saat halaman ditampilkan
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) {
                offsetX.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            } else {
                offsetY.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }, // Offset animasi
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }

    @Composable
    fun SlideOut(
        durationMillis: Int = 3000,
        delayMillis: Int = 0, // Parameter waktu delay
        slideDirection: SlideDirection = SlideDirection.Down,
        content: @Composable () -> Unit
    ) {
        // Tentukan nilai akhir berdasarkan arah
        val targetOffset = when (slideDirection) {
            SlideDirection.Left -> slideOffset
            SlideDirection.Right -> -slideOffset
            SlideDirection.Up -> slideOffset
            SlideDirection.Down -> -slideOffset
        }

        val offsetX = remember { Animatable(0f) }
        val offsetY = remember { Animatable(0f) }

        // Animasi slide saat halaman keluar
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) {
                offsetX.animateTo(
                    targetValue = targetOffset,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            } else {
                offsetY.animateTo(
                    targetValue = targetOffset,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }, // Offset animasi
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }

    @Composable
    fun FadeIn(
        durationMillis: Int = 1000,
        delayMillis: Int = 0, // Parameter waktu delay
        content: @Composable () -> Unit
    ) {
        // Animasi alpha dimulai dari 0 (tidak terlihat)
        val alpha = remember { Animatable(0f) }

        // Animasi fade in
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            alpha.animateTo(
                targetValue = 1f, // Menuju 100% opacity
                animationSpec = tween(durationMillis = durationMillis)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = alpha.value), // Mengatur alpha berdasarkan animasi
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    fun FadeOut(
        durationMillis: Int = 1000,
        delayMillis: Int = 0, // Parameter waktu delay
        content: @Composable () -> Unit
    ) {
        // Animasi alpha dimulai dari 1 (sepenuhnya terlihat)
        val alpha = remember { Animatable(1f) }

        // Animasi fade out
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            alpha.animateTo(
                targetValue = 0f, // Menuju 0% opacity
                animationSpec = tween(durationMillis = durationMillis)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = alpha.value), // Mengatur alpha berdasarkan animasi
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    fun SlideInFadeIn(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        slideDirection: SlideDirection = SlideDirection.Up,
        content: @Composable () -> Unit
    ) {
        // Tentukan nilai awal offset berdasarkan arah slide
        val initialOffset = when (slideDirection) {
            SlideDirection.Left -> -slideOffset
            SlideDirection.Right -> slideOffset
            SlideDirection.Up -> -slideOffset
            SlideDirection.Down -> slideOffset
        }

        // Animasi offset dan alpha
        val offsetX = remember { Animatable(if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) initialOffset else 0f) }
        val offsetY = remember { Animatable(if (slideDirection == SlideDirection.Up || slideDirection == SlideDirection.Down) initialOffset else 0f) }
        val alpha = remember { Animatable(0f) }

        // Animasi masuk
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            launch {
                if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) {
                    offsetX.animateTo(0f, animationSpec = tween(durationMillis))
                } else {
                    offsetY.animateTo(0f, animationSpec = tween(durationMillis))
                }
            }
            launch {
                alpha.animateTo(1f, animationSpec = tween(durationMillis))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
                .graphicsLayer(alpha = alpha.value),
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }

    @Composable
    fun SlideOutFadeOut(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        slideDirection: SlideDirection = SlideDirection.Down,
        content: @Composable () -> Unit
    ) {
        // Tentukan nilai akhir offset berdasarkan arah slide
        val targetOffset = when (slideDirection) {
            SlideDirection.Left -> -slideOffset
            SlideDirection.Right -> slideOffset
            SlideDirection.Up -> -slideOffset
            SlideDirection.Down -> slideOffset
        }

        // Animasi offset dan alpha
        val offsetX = remember { Animatable(0f) }
        val offsetY = remember { Animatable(0f) }
        val alpha = remember { Animatable(1f) }

        // Animasi keluar
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong()) // Tunggu sesuai waktu delay
            launch {
                if (slideDirection == SlideDirection.Left || slideDirection == SlideDirection.Right) {
                    offsetX.animateTo(targetOffset, animationSpec = tween(durationMillis))
                } else {
                    offsetY.animateTo(targetOffset, animationSpec = tween(durationMillis))
                }
            }
            launch {
                alpha.animateTo(0f, animationSpec = tween(durationMillis))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
                .graphicsLayer(alpha = alpha.value),
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }

    @Composable
    fun ZoomIn(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        initialScale: Float = 0.5f, // Skala awal zoom-in
        content: @Composable () -> Unit
    ) {
        val scale = remember { Animatable(initialScale) }

        // Animasi scale
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong())
            scale.animateTo(
                targetValue = 1f, // Menuju skala normal
                animationSpec = tween(durationMillis = durationMillis)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    fun ZoomOut(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        targetScale: Float = 0.5f, // Skala akhir zoom-out
        content: @Composable () -> Unit
    ) {
        val scale = remember { Animatable(1f) }

        // Animasi scale
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong())
            scale.animateTo(
                targetValue = targetScale, // Menuju skala yang lebih kecil
                animationSpec = tween(durationMillis = durationMillis)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    fun ZoomInFadeIn(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        initialScale: Float = 0.5f, // Skala awal zoom-in
        content: @Composable () -> Unit
    ) {
        val scale = remember { Animatable(initialScale) }
        val alpha = remember { Animatable(0f) }

        // Animasi scale dan alpha
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong())
            launch {
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    alpha = alpha.value
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    fun ZoomOutFadeOut(
        durationMillis: Int = 1000,
        delayMillis: Int = 0,
        targetScale: Float = 0.5f, // Skala akhir zoom-out
        content: @Composable () -> Unit
    ) {
        val scale = remember { Animatable(1f) }
        val alpha = remember { Animatable(1f) }

        // Animasi scale dan alpha
        LaunchedEffect(Unit) {
            delay(delayMillis.toLong())
            launch {
                scale.animateTo(
                    targetValue = targetScale,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = durationMillis)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    alpha = alpha.value
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

}




