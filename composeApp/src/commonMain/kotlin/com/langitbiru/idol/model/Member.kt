package com.langitbiru.idol.model

data class Member(
    val id: Int,
    val fullname: String,
    val nickname: String? = null,
    val generation: Int,
    val status: String,
    val isActive: Boolean = true,
    val hometown: String? = null,
    val birthplace: String? = null,
    val birthday: String? = null,
    val debutDate: String? = null
) {


    fun getKabeshaUrl(): String {
        return "https://asset1315.vercel.app/idol/kabesha/$nickname.jpeg"
    }
    fun getKabeshaTransparentUrl(): String {
        return "https://asset1315.vercel.app/idol/kabesha_transparent/$nickname.png"
    }
}


