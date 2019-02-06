package com.thinkdevs.security.repositories

import com.thinkdevs.security.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository:MongoRepository<User, String> {
    fun findByEmail(email:String):User
}