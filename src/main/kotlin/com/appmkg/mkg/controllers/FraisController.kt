package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.FormationDto
import com.appmkg.mkg.dtos.FraisDto
import com.appmkg.mkg.dtos.Message
import com.appmkg.mkg.dtos.RegisterDto
import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Frais
import com.appmkg.mkg.models.User
import com.appmkg.mkg.repositories.FormationRepository
import com.appmkg.mkg.services.FormationService
import com.appmkg.mkg.services.FraisService
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
@RequestMapping("api/frais")
class FraisController(private val fraisService: FraisService) {
    @PostMapping
    fun addFrais(@RequestBody body: FraisDto): ResponseEntity<Frais> {
        val frais = Frais()
        frais.name = body.name
        frais.montmensuel = body.montmensuel

        return ResponseEntity.ok(this.fraisService.save(frais))
    }

    @GetMapping
    fun getFrais(): ResponseEntity<Collection<Frais>> = ResponseEntity
        .ok(this.fraisService.getFrais())

    @GetMapping("/{id}")
    fun getFraisById(@PathVariable id:Int): ResponseEntity<Any> {
        try {
            if(fraisService.getById(id) != null){
                return ResponseEntity.ok(this.fraisService.getById(id))
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
            if(fraisService.getById(id) != null){
                fraisService.deleteFrais(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
               return ResponseEntity.badRequest().body(Message("not fund"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("not fund"))
        }



    }

    @PatchMapping("/{id}")
    fun updateFrais(@PathVariable id:Int,@RequestBody body: FraisDto): ResponseEntity<Any>?{
        try {
            val frais= fraisService.getById(id)

            if (frais != null) {

                //val formation = Formation()
                frais.name = body.name
                frais.montmensuel = body.montmensuel
            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.fraisService.save(frais))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
        }
    }

}