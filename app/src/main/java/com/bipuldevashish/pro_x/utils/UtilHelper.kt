package com.bipuldevashish.pro_x.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast


object UtilHelper {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    fun isFirstTimeAppOpened(context: Context):Boolean {
        var pref: SharedPreferences = context.getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)
        return pref.getBoolean("activity_executed", true)
    }

}