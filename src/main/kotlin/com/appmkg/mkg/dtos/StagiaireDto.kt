package com.appmkg.mkg.dtos

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Paiement
import com.appmkg.mkg.models.Stagiaire

class StagiaireDto {
    val forma = Formation()
    val id: Int=0
    val name = ""
    val postnom = ""
    val prenom = ""
    val lieuNais = ""
    val datenais = ""
    val sexe = ""
    val etatCivil = ""
    val niveau_etude = ""
    val adresse = ""
    val telephone = ""
    val email = ""
    val langue_parle=""
    val jours = ""
    val dateInscription = ""
    val formation: Formation = forma
}