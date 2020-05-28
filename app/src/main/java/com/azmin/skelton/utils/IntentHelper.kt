package com.azmin.skelton.utils

import android.content.Context
import android.content.Intent
import com.azmin.skelton.main.MainActivity


object IntentHelper {

    fun startHomeActivity(context: Context) {
        var intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}