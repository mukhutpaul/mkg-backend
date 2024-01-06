package com.appmkg.mkg.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import org.aspectj.apache.bcel.classfile.Module.Open
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import java.util.Date

@Entity
class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    var id: Int =0

    @Column(name = "datePaie")
    var datePaie = ""

    @Column(name = "mois")
    var mois = ""

    @Column()
    var montant : Int =0

    @ManyToOne()
    @JoinColumn(name = "stagiaire_id")
    var stagiaire : Stagiaire? = null

    @ManyToOne()
    @JoinColumn(name = "formation_id")
    var formation : Formation? = null

    @ManyToOne()
    @JoinColumn(name = "tranche_id")
    var tranche: Tranche? = null

    }
