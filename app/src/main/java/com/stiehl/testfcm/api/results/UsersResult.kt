package com.stiehl.testfcm.api.results

import com.stiehl.testfcm.model.User

data class UsersResult(
    val status: String,
    val data: DataResult
) {
    inner class DataResult(val users: List<User>)
}