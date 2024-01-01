package com.appmkg.mkg.repositories

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Frais
import com.appmkg.mkg.models.Tranche
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder


interface TrancheRepository : JpaRepository<Tranche,Int> {

    @Query("select * from Tranche order by id desc", nativeQuery = true)
    fun getTranches(): Collection<Tranche>?


    @Modifying
    @Query("update Formation f SET f.title =:title,f.coutformation =:coutformation,f.duree =:duree WHERE f.id =:id")
    fun updateFormation(@Param("title") title: String?,@Param("coutformation") coutformation: Int?,@Param("duree") duree: String?,  @Param("id") id: Int)


}