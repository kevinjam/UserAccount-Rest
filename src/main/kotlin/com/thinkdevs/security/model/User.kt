package com.thinkdevs.security.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef


@Document(collection = "user")
class User{
    @Id
     val id: String? = null
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
     val email: String? = null
     var password: String? = null
     val fullname: String? = null
     var enabled: Boolean = false
    @DBRef
    var roles: Set<Role>? = null
}
