package com.stiehl.testfcm.model

import java.io.Serializable

data class Message(
    val title: String,
    val body: String,
    val fromName: String,
    val fromId: String
): Serializable