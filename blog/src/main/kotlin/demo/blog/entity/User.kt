package demo.blog.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class User(
        var login: String,
        var username: String,
        var description: String? = null,
        @Id @GeneratedValue var id:Long? = null
)