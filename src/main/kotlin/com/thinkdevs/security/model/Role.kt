package com.thinkdevs.security.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "role")
class Role {
    @Id
    lateinit var  id:String
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
     val role: String? = null

}