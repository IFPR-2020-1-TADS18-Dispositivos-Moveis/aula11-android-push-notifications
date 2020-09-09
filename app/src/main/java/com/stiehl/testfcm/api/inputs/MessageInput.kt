package com.stiehl.testfcm.api.inputs

data class MessageInput(
    val from: String,
    val to: List<String>,
    val title: String,
    val body: String
)