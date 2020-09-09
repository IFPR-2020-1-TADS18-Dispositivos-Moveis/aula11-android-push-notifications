package com.stiehl.testfcm.model

import java.io.Serializable
import java.lang.Exception

data class User(val id: String, val name: String) : Serializable {
    override fun equals(other: Any?): Boolean {
        return try {
            id == (other as User).id
        } catch (e: Exception) {
            false
        }
    }
}