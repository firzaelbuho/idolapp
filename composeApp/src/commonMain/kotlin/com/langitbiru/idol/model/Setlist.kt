package com.langitbiru.idol.model

data class Setlist(
    val id: Int,
    val titleId: String,
    val titleJp: String,
    val desc: String? = null,
    val tracklist: List<Song> = emptyList()
)
