package com.thinkdevs.security.controller

import com.thinkdevs.security.model.User
import com.thinkdevs.security.services.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @Autowired
    lateinit var userService: CustomUserDetailsService


    @RequestMapping(value = "/signup", method = [RequestMethod.POST])
    fun createNewUser(@RequestBody user:User, errors: Errors){
        val userExists = userService.findUserByEmail(user.email!!)
println("User is here $userExists")
//
//        if (userExists != null) {
//           println(" \"There is already a user registered with the username provided\"")
//        }
//
//        if (errors.hasErrors()){
//            println("signup")
//        }else{
//            userService.saveUser(user)
//            println("User has been registered successfully")
//        }
    }


    //login
    fun login(){

    }


}