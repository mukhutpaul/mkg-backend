package com.appmkg.mkg.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    var id: Int =0

    @Column(name="name")
    var name = ""

    @Column(name="postnom")
    var postnom = ""

    @Column(name="prenom")
    var prenom = ""

    @Column(name="lieuNais")
    var lieuNais = ""

    @Column(name="datenais")
    var datenais = ""

    @Column(length = 2, name = "sexe")
    var sexe = ""

    @Column(name="etatCivil")
    var etatCivil = ""

    @Column(name="niveau_etude")
    var niveau_etude = ""

    @Column(name="adresse")
    var adresse = ""

    @Column(name = "telephone")
    var telephone = ""

    @Column(name="email")
    var email = ""

    @Column(name = "langue_parle")
    var langue_parle = ""

    @Column(name="jours")
    var jours = ""

    @Column(name="dateInscription")
    var dateInscription = ""


    @ManyToOne()
    @JoinColumn(name = "formation_id")
    var formation  : Formation? = null

}