package com.appmkg.mkg.dtos

import com.appmkg.mkg.models.Formation
import com.appmkg.mkg.models.Paiement
import com.appmkg.mkg.models.Stagiaire
import com.appmkg.mkg.models.Tranche

class PaiementDto {
    val sta = Stagiaire()
    val tran = Tranche()
    val ft = Formation()
    val id: Int=0
    val datePaie = ""
    val mois = ""
    val montant: Int = 0
    val stagiaire: Stagiaire = sta
    val formation : Formation = ft
    val tranche : Tranche = tran

}