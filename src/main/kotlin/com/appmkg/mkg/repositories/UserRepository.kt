package com.appmkg.mkg.repositories

import com.appmkg.mkg.models.Tranche
import com.appmkg.mkg.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User,Int> {

    @Query("select * from User order by id desc", nativeQuery = true)
    fun getUsers(): Collection<User>?

    fun findByEmail(email:String): User?
}