package com.appmkg.mkg.services

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FormationService(private val formationRepository: FormationRepository) {
    fun save(formation: Formation): Formation {
        return this.formationRepository.save(formation)
    }

   // fun findByName(name: String): Formation? {
    //    return this.formationRepository.findByFormation(name)
    //}

    fun getById(id: Int): Formation {
        return this.formationRepository.findById(id).orElseThrow()
    }

    fun getFormations(): Collection<Formation> ?= this.formationRepository.getFormation()

    fun deleteFormation(id: Int): Unit = formationRepository.deleteById(id)

    fun updateFormat(title:String, coutformation: Int,duree:String,id:Int)= this.formationRepository.updateFormation(title,coutformation,duree,id)
}