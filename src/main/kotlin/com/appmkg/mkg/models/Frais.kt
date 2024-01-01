package com.appmkg.mkg.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
class Frais {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int =0

    @Column
    var name = ""

    var montmensuel : Int = 0

}