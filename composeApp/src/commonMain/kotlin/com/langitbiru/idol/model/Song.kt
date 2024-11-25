package com.langitbiru.idol.model

data class Song(
    val id: Int,
    val titleId: String,
    val titleJp: String,
    val lyric: String? = null,
    val description: String? = null
)