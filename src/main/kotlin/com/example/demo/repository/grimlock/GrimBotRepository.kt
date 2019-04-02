package com.example.demo.repository.grimlock

import com.example.demo.model.grimlock.GrimBot
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface GrimBotRepository: ReactiveCrudRepository<GrimBot, String>