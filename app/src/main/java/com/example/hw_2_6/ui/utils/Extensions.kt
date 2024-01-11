package com.example.hw_2_6.ui.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}