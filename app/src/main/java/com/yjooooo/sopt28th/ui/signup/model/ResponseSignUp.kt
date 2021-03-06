package com.yjooooo.sopt28th.ui.signup.model

import com.google.gson.annotations.SerializedName

data class ResponseSignUp(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("nickname")
        val nickname: String
    )
}