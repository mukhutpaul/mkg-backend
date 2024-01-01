package com.appmkg.mkg.services

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Stagiaire
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(user:User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun getById(id: Int): User {
        return this.userRepository.findById(id).orElseThrow()
    }

    fun getUsers(): Collection<User> ?= this.userRepository.getUsers()
    fun deleteUser(id: Int): Unit = userRepository.deleteById(id)
}