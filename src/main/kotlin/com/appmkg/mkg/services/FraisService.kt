package com.appmkg.mkg.services

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Frais
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.FraisRepository
import com.appmkg.mkg.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FraisService(private val fraisRepository: FraisRepository) {
    fun save(frais: Frais): Frais {
        return this.fraisRepository.save(frais)
    }

   // fun findByName(name: String): Formation? {
    //    return this.formationRepository.findByFormation(name)
    //}

    fun getById(id: Int): Frais {
        return this.fraisRepository.findById(id).orElseThrow()
    }

    fun getFrais(): Collection<Frais> ?= this.fraisRepository.getFrais()

    fun deleteFrais(id: Int): Unit = fraisRepository.deleteById(id)


}