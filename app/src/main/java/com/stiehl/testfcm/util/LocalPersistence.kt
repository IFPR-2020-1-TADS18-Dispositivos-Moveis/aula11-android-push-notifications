package com.stiehl.testfcm.util

import android.content.Context

class LocalPersistence(private val context: Context) {
    var userId: String?
        get() {
            val sharedPref = context.getSharedPreferences("firebase", Context.MODE_PRIVATE)
            return sharedPref!!.getString("id", null)
        }
        set(id) {
            val sharedPref = context.getSharedPreferences("firebase", Context.MODE_PRIVATE)
            sharedPref!!.edit().apply {
                putString("id", id)
                commit()
            }
        }
}