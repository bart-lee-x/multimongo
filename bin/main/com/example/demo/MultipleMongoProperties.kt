package com.example.demo

import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mongodb")
class MultipleMongoProperties {

    var failsafe = MongoProperties()
    var metaBeta = MongoProperties()
}
