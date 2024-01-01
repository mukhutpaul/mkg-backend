package com.appmkg.mkg.repositories

import com.appmkg.mkg.dtos.HomeDto
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Stagiaire
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder


interface StagiaireRepository : JpaRepository<Stagiaire,Int> {


    @Query("select * from Stagiaire order by id desc", nativeQuery = true)
    fun getStagiaires(): Collection<Stagiaire>

    @Query("select count(s.id) as nombre,f.title as titre from Stagiaire as s,Formation as f where s.formation_id = f.id group by s.formation_id", nativeQuery = true)
    fun getStageFormation(): Collection<Any>?

    @Modifying
    @Query("update Formation f SET f.title =:title,f.coutformation =:coutformation,f.duree =:duree WHERE f.id =:id")
    fun updateFormation(@Param("title") title: String?,@Param("coutformation") coutformation: Int?,@Param("duree") duree: String?,  @Param("id") id: Int)


}