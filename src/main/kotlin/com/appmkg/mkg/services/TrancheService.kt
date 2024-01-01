package com.appmkg.mkg.services

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Frais
import com.appmkg.mkg.models.Tranche
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.FraisRepository
import com.appmkg.mkg.repositories.TrancheRepository
import com.appmkg.mkg.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TrancheService(private val trancheRepository: TrancheRepository) {
    fun save(tranche: Tranche): Tranche {
        return this.trancheRepository.save(tranche)
    }

   // fun findByName(name: String): Formation? {
    //    return this.formationRepository.findByFormation(name)
    //}

    fun getById(id: Int): Tranche {
        return this.trancheRepository.findById(id).orElseThrow()
    }

    fun getTranches(): Collection<Tranche> ?= trancheRepository.findAll()

    fun deleteTrance(id: Int): Unit = trancheRepository.deleteById(id)


}