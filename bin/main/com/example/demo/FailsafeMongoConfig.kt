package com.example.demo

import com.example.demo.repository.AgitThreadRepository
import com.example.demo.repository.grimlock.GrimBotRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [AgitThreadRepository::class], reactiveMongoTemplateRef = "failsafeMongoTemplate")
class FailsafeMongoConfig

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [GrimBotRepository::class], reactiveMongoTemplateRef = "metaBetaMongoTemplate")
class MetaBetaMongoConfig