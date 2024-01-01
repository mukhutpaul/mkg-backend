package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.FormationDto
import com.appmkg.mkg.dtos.Message
import com.appmkg.mkg.dtos.RegisterDto
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.services.FormationService
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
@RequestMapping("api/formation")
class FormationController(private val formationService: FormationService,private val formationRepository: FormationRepository) {

    @PostMapping
    fun addFormation(@RequestBody body: FormationDto): ResponseEntity<Formation> {
        val formation = Formation()
        formation.title = body.title
        formation.coutformation = body.coutformation
        formation.duree = body.duree

        return ResponseEntity.ok(this.formationService.save(formation))
    }

    @GetMapping
    fun getFormation(): ResponseEntity<Collection<Formation>> = ResponseEntity
        .ok(this.formationService.getFormations())

    @GetMapping("/{id}")
    fun getFormationById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            if(formationService.getById(id) != null){
                return ResponseEntity.ok(this.formationService.getById(id))
            }else{
                return ResponseEntity.badRequest().body(Message("Not found"))
            }
        }catch (e: Exception){
                return ResponseEntity.badRequest().body(Message("Not found"))
        }


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFormation(@PathVariable id:Int):ResponseEntity<Any> {
        try {
            if(formationService.getById(id) != null){
                formationService.deleteFormation(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
               return ResponseEntity.badRequest().body(Message("not fund"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("not fund"))
        }



    }



    @PatchMapping("/{id}")
    fun updateFormation(@PathVariable id:Int,@RequestBody body: FormationDto): ResponseEntity<Any>?{
        try {
            val idform = formationService.getById(id)

            if (idform != null) {

                //val formation = Formation()
                idform.title = body.title
                idform.coutformation = body.coutformation
                idform.duree = body.duree
            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.formationService.save(idform))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non"))
        }
    }

}