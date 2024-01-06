package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.*
import com.appmkg.mkg.models.Stagiaire
import com.appmkg.mkg.services.StagiaireService
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
@RequestMapping("      ")
class StagiaireController(private val stagiaireService: StagiaireService) {

    @PostMapping
    fun addStagiaire(@RequestBody body: StagiaireDto): ResponseEntity<Stagiaire> {
        val stagiaire = Stagiaire()
        stagiaire.name = body.name
        stagiaire.postnom = body.postnom
        stagiaire.prenom = body.prenom
        stagiaire.lieuNais = body.lieuNais
        stagiaire.datenais = body.datenais
        stagiaire.sexe = body.sexe
        stagiaire.etatCivil = body.etatCivil
        stagiaire.niveau_etude = body.niveau_etude
        stagiaire.adresse = body.adresse
        stagiaire.telephone = body.telephone
        stagiaire.email = body.email
        stagiaire.langue_parle = body.langue_parle
        stagiaire.jours = body.jours
        stagiaire.dateInscription = body.dateInscription
        stagiaire.formation = body.formation

        return ResponseEntity.ok(this.stagiaireService.save(stagiaire))
    }

    @GetMapping
    fun getStagiaires(): ResponseEntity<Collection<Stagiaire>> = ResponseEntity
        .ok(this.stagiaireService.getStagiaires())

    @GetMapping("/formation")
    fun getStageFormation(): ResponseEntity<Collection<Any>>{
       var tab : Array<String>
        return ResponseEntity
            .ok(this.stagiaireService.getStageFormation())

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            if(stagiaireService.getById(id) != null){
                return ResponseEntity.ok(this.stagiaireService.getById(id))
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
            if(stagiaireService.getById(id) != null){
                stagiaireService.deleteStagiaire(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
               return ResponseEntity.badRequest().body(Message("not fund"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("not fund"))
        }



    }



    @PatchMapping("/{id}")
    fun updateStagiaire(@PathVariable id:Int,@RequestBody body: StagiaireDto): ResponseEntity<Any>?{
        try {
            val stagiaire = stagiaireService.getById(id)

            if (stagiaire != null) {
                //val formation = Formation()
                stagiaire.name = body.name
                stagiaire.postnom = body.postnom
                stagiaire.prenom = body.prenom
                stagiaire.lieuNais = body.lieuNais
                stagiaire.datenais = body.datenais
                stagiaire.sexe = body.sexe
                stagiaire.etatCivil = body.etatCivil
                stagiaire.niveau_etude = body.niveau_etude
                stagiaire.adresse = body.adresse
                stagiaire.telephone = body.telephone
                stagiaire.email = body.email
                stagiaire.jours = body.jours
                stagiaire.dateInscription = body.dateInscription
                stagiaire.formation = body.formation

            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.stagiaireService.save(stagiaire))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
        }
    }

}