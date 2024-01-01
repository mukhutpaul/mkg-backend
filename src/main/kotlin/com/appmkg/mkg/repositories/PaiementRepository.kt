package com.appmkg.mkg.repositories

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Paiement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder


interface PaiementRepository : JpaRepository<Paiement,Int> {

    @Query("select * from Paiement order by id desc", nativeQuery = true)
    fun getPaiements(): Collection<Paiement>?

    @Query("select sum(montant),mois from Paiement group by mois", nativeQuery = true)
    fun getMensuel(): Collection<Any>?

    @Query("select sum(montant),substr(date_paie,1,5)  from Paiement where substr(date_paie,1,2) = substr(DATE_FORMAT(NOW(),'%d%m%Y%H%i%s'),1,2) group by substr(date_paie,1,2)", nativeQuery = true)
    fun getJour(): Collection<Any>?


    @Modifying
    @Query("update Formation f SET f.title =:title,f.coutformation =:coutformation,f.duree =:duree WHERE f.id =:id")
    fun updateFormation(@Param("title") title: String?,@Param("coutformation") coutformation: Int?,@Param("duree") duree: String?,  @Param("id") id: Int)


}