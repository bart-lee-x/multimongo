package com.example.demo.model.grimlock

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "bot")
data class GrimBot(
        val actions: List<Any>,
        val config: Config,
        val entities: List<Entity>,
        val fallbacks: List<Fallback>,
        val fulfillmentUri: Any,
        val intents: List<Intent>,
        @Indexed val name: String,
        val userId: String
) {
    data class Config(
            val isMini: Boolean
    )

    data class Intent(
            val id: String,
            val name: String
    )

    data class Fallback(
            val _id: Int,
            val text: String
    )

    data class Entity(
            val id: String,
            val name: String
    )
}