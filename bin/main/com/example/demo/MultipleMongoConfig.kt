package com.example.demo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@Configuration
@EnableConfigurationProperties(MultipleMongoProperties::class)
class MultipleMongoConfig(val mongoProperties: MultipleMongoProperties): AbstractReactiveMongoConfiguration() {

//    override fun reactiveMongoClient(): MongoClient {
//        val mongo = mongoProperties.failsafe
//        val conString = ConnectionString("mongodb://${mongo.host}:${mongo.port}/${mongo.database}")
//
//        val settings = MongoClientSettings.builder()
//                .applyConnectionString(conString)
//                .build()
//
//        return MongoClients.create(settings)
//    }

    override fun reactiveMongoClient(): MongoClient = MongoClients.create("mongodb://${mongoProperties.failsafe.host}:27017/failsafe")

    override fun getDatabaseName(): String {
        return "failsafe"
    }


    @Bean
    @Primary
    @Throws(Exception::class)
    fun failsafeMongoTemplate(): ReactiveMongoTemplate {
        val mongoClient = MongoClients.create("mongodb://${mongoProperties.failsafe.host}:27017/failsafe")
        return ReactiveMongoTemplate(mongoClient, databaseName)
    }

    @Bean
    @Throws(Exception::class)
    fun metaBetaMongoTemplate(): ReactiveMongoTemplate {
        val b = mongoProperties.metaBeta
        val c = MongoCredential.createCredential(b.username, b.authenticationDatabase, b.password)
        val mongoSetting = MongoClientSettings.builder()
                .applyToClusterSettings { it.hosts(listOf(ServerAddress(b.host, b.port))).build() }
                .credential(c)
                .build()
        val mongoClient = MongoClients.create(mongoSetting)
        return ReactiveMongoTemplate(mongoClient, databaseName)
    }

//    @Bean
//    @Primary
//    @Throws(Exception::class)
//    fun failsafeFactory(mongo: MongoProperties): ReactiveMongoDatabaseFactory {
//        return SimpleReactiveMongoDatabaseFactory(
//                ConnectionString("mongodb://${mongo.host}:${mongo.port}/${mongo.database}")
//        )
//    }
}