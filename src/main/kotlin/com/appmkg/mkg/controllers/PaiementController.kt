package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.FormationDto
import com.appmkg.mkg.dtos.Message
import com.appmkg.mkg.dtos.PaiementDto
import com.appmkg.mkg.dtos.RegisterDto
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Paiement
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.repositories.PaiementRepository
import com.appmkg.mkg.services.FormationService
import com.appmkg.mkg.services.PaiementService
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
@RequestMapping("api/paiement")
class PaiementController(private val paiementService: PaiementService) {

    @PostMapping
    fun addFormation(@RequestBody body: PaiementDto): ResponseEntity<Paiement> {
        val paiement = Paiement()
        paiement.datePaie = body.datePaie
        paiement.mois = body.mois
        paiement.montant = body.montant
        paiement.stagiaire  = body.stagiaire
        paiement.formation = body.formation
        paiement.tranche = body.tranche

        return ResponseEntity.ok(this.paiementService.save(paiement))
    }

    @GetMapping
    fun getPaiements(): ResponseEntity<Collection<Paiement>> = ResponseEntity
        .ok(this.paiementService.getPaiements())

    @GetMapping("/mensuel")
    fun getMensuel(): ResponseEntity<Collection<Any>> = ResponseEntity
        .ok(this.paiementService.getMensuel())

    @GetMapping("/jour")
    fun getJour(): ResponseEntity<Collection<Any>> = ResponseEntity
        .ok(this.paiementService.getJour())

    @GetMapping("/{id}")
    fun getPaiementById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            if(paiementService.getById(id) != null){
                return ResponseEntity.ok(this.paiementService.getById(id))
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
            if(paiementService.getById(id) != null){
                paiementService.deletePaiement(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
               return ResponseEntity.badRequest().body(Message("not found"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("not found"))
        }



    }



    @PatchMapping("/{id}")
    fun updateFormation(@PathVariable id:Int,@RequestBody body: PaiementDto): ResponseEntity<Any>?{
        try {
            val paiement = Paiement()

            if (paiement != null) {

                paiement.datePaie = body.datePaie
                paiement.montant = body.montant
                paiement.stagiaire = body.stagiaire
                paiement.formation = body.formation
                paiement.tranche = body.tranche

            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.paiementService.save(paiement))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non"))
        }
    }

}