package com.stiehl.testfcm.api.results

data class RegisterResult(
    val status: String,
    val data: DataResult
) {
    inner class DataResult(val id: String)
}