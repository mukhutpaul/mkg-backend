package com.appmkg.mkg.services

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Paiement
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.PaiementRepository
import com.appmkg.mkg.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PaiementService(private val paiementRepository: PaiementRepository) {
    fun save(paiement: Paiement): Paiement {
        return this.paiementRepository.save(paiement)
    }

   // fun findByName(name: String): Formation? {
    //    return this.formationRepository.findByFormation(name)
    //}

    fun getById(id: Int): Paiement {
        return this.paiementRepository.findById(id).orElseThrow()
    }

    fun getPaiements(): Collection<Paiement> ?= this.paiementRepository.getPaiements()

    fun getMensuel(): Collection<Any> ?= this.paiementRepository.getMensuel()

    fun getJour(): Collection<Any> ?= this.paiementRepository.getJour()

    fun deletePaiement(id: Int): Unit = paiementRepository.deleteById(id)

}