package com.appmkg.mkg.repositories

import com.appmkg.mkg.models.Formation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder


interface FormationRepository : JpaRepository<Formation,Int> {

    @Query("select * from Formation order by id DESC", nativeQuery = true)
    fun getFormation(): Collection<Formation>?

    @Modifying
    @Query("update Formation f SET f.title =:title,f.coutformation =:coutformation,f.duree =:duree WHERE f.id =:id")
    fun updateFormation(@Param("title") title: String?,@Param("coutformation") coutformation: Int?,@Param("duree") duree: String?,  @Param("id") id: Int)


}