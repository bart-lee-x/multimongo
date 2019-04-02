package com.example.demo

import com.example.demo.repository.grimlock.GrimBotRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

//@SpringBootApplication
@SpringBootApplication(exclude = [ MongoAutoConfiguration::class, MongoDataAutoConfiguration::class, MongoReactiveAutoConfiguration::class, MongoReactiveDataAutoConfiguration::class])
@EnableReactiveMongoRepositories
class MultiMongoApplication {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun init(grimBotRepository: GrimBotRepository) = CommandLineRunner {
        val l = grimBotRepository.findAll()



        log.info(l.blockFirst()?.name)

    }
}

fun main(args: Array<String>) {
    runApplication<MultiMongoApplication>(*args)
}
