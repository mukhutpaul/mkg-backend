package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.*
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Frais
import com.appmkg.mkg.models.Tranche
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.services.FormationService
import com.appmkg.mkg.services.FraisService
import com.appmkg.mkg.services.TrancheService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/tranche")
class TrancheController(private val trancheService: TrancheService) {
    @PostMapping
    fun addTranche(@RequestBody body: TrancheDto): ResponseEntity<Tranche> {
        val tranche = Tranche()
        tranche.name = body.name


        return ResponseEntity.ok(this.trancheService.save(tranche))
    }

    @GetMapping
    fun getTranches(): ResponseEntity<Collection<Tranche>> = ResponseEntity
        .ok(this.trancheService.getTranches())

    @GetMapping("/{id}")
    fun getFraisById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            if(trancheService.getById(id) != null){
                return ResponseEntity.ok(this.trancheService.getById(id))
            }else{
                return ResponseEntity.badRequest().body(Message("Not found"))
            }
        }catch (e: Exception){
                return ResponseEntity.badRequest().body(Message("Not found"))
        }


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFrais(@PathVariable id:Int):ResponseEntity<Any> {
        try {
            if(trancheService.getById(id) != null){
                trancheService.deleteTrance(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
               return ResponseEntity.badRequest().body(Message("not fund"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("not fund"))
        }



    }

    @PatchMapping("/{id}")
    fun updateFrais(@PathVariable id:Int,@RequestBody body: TrancheDto): ResponseEntity<Any>?{
        try {
            val tranche= trancheService.getById(id)

            if (tranche != null) {

                //val formation = Formation()
                tranche.name = body.name
            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.trancheService.save(tranche))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
        }
    }

}