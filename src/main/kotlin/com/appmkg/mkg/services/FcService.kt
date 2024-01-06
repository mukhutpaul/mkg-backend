package com.appmkg.mkg.services

import com.appmkg.mkg.models.Stagiaire
import com.appmkg.mkg.repositories.StagiaireRepository
import org.springframework.stereotype.Service

@Service
class StagiaireService(private val stagiaireRepository: StagiaireRepository) {
    fun save(stagiaire: Stagiaire): Stagiaire {
        return this.stagiaireRepository.save(stagiaire)
    }

   // fun findByName(name: String): Formation? {
    //    return this.formationRepository.findByFormation(name)
    //}

    fun getById(id: Int): Stagiaire {
        return this.stagiaireRepository.findById(id).orElseThrow()
    }

    fun getStagiaires(): Collection<Stagiaire> ?= this.stagiaireRepository.getStagiaires()

    fun getStageFormation(): Collection<Any> ?= this.stagiaireRepository.getStageFormation()

    fun deleteStagiaire(id: Int): Unit = stagiaireRepository.deleteById(id)

}