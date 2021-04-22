package com.yjooooo.sopt28th.util

import android.content.Context
import android.widget.Toast

fun Context.toastMessageUtil(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}