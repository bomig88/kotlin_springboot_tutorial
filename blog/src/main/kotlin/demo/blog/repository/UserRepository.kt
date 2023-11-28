package demo.blog.repository

import demo.blog.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}