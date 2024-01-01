package com.appmkg.mkg.services

import com.appmkg.mkg.dtos.HomeDto
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Stagiaire
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.StagiaireRepository
import com.appmkg.mkg.repositories.UserRepository
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
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