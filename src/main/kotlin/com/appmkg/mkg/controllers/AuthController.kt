package com.appmkg.mkg.controllers

import com.appmkg.mkg.dtos.LoginDto
import com.appmkg.mkg.dtos.Message
import com.appmkg.mkg.dtos.RegisterDto
import com.appmkg.mkg.dtos.TrancheDto
import com.appmkg.mkg.models.Tranche
import com.appmkg.mkg.models.User
import com.appmkg.mkg.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.persistence.PostUpdate
import jakarta.servlet.http.HttpServletResponse
import org.apache.tomcat.util.http.parser.Cookie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.KeyFactory
import java.security.PrivateKey

import java.util.*

@RestController
@RequestMapping("api")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDto): ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password
        user.role = body.role

        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDto, response : HttpServletResponse): ResponseEntity<Any>{

       val user = this.userService.findByEmail(body.email)
           ?: return ResponseEntity.badRequest().body(Message("user not found"))

        if(!user.comparePassword(body.password)){
            return ResponseEntity.badRequest().body(Message("Invalid password"))
        }

        val issuer = user.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512,"secret").compact()

        val cookie = jakarta.servlet.http.Cookie("jwt",jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(this.userService.getById(issuer.toInt()))
    }

    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt : String?): ResponseEntity<Any>{


      try {

        if(jwt == null) {
            return ResponseEntity.status(401).body(Message("unauthentificated"))
        }

          val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

          return ResponseEntity.ok(this.userService.getById(body.issuer.toInt()))


    }catch (e : Exception){

          return ResponseEntity.status(401).body(Message("EEEEH"))
    }
    }
    @PostMapping("logout")
    fun logou(response : HttpServletResponse): ResponseEntity<Any>{

        val cookie = jakarta.servlet.http.Cookie("jwt","")
        cookie.maxAge =0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Success"))

    }

    @GetMapping("users")
    fun getUsers(): ResponseEntity<Collection<User>> = ResponseEntity
            .ok(this.userService.getUsers())

    @PatchMapping("/users/{id}")
    fun updateUser(@PathVariable id:Int, @RequestBody body: RegisterDto): ResponseEntity<Any>?{
        try {
            val user= userService.getById(id)

            if (user != null) {

                //val formation = Formation()
                user.name = body.name
                user.email = body.email
                user.password =body.password
                user.role = body.role

            } else {
                return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
            }

            return ResponseEntity.ok(this.userService.save(user))
        }catch (e:Exception){
            return ResponseEntity.badRequest().body(Message("Identifiant non trouvé"))
        }
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id:Int):ResponseEntity<Any> {
        try {
            if(userService.getById(id) != null){
                userService.deleteUser(id)
                return ResponseEntity.ok().body(Message("Supression réusie"))
            }else {
                return ResponseEntity.badRequest().body(Message("Id not fund"))
            }

        }catch (e: Exception){
            return ResponseEntity.badRequest().body(Message("Id not fund"))
        }



    }
}