package com.example.demo.repository

import com.example.demo.AgitThread
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

interface AgitThreadRepository: ReactiveCrudRepository<AgitThread, String>