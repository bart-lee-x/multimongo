package com.example.demo

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "readMessages")
data class AgitThread (
        @Indexed val messageId: Int,
        val thread: Thread
)