/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkdevs.security.services


import com.thinkdevs.security.model.Role
import com.thinkdevs.security.model.User
import com.thinkdevs.security.repositories.RoleRepository
import com.thinkdevs.security.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import java.util.*

/**
 *
 * @author
 */
@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    private val userRepository: UserRepository? = null
    @Autowired
    private val roleRepository: RoleRepository? = null
    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    fun findUserByEmail(email: String): User {
        return userRepository!!.findByEmail(email)
    }

    fun saveUser(user: User) {
        user.password = bCryptPasswordEncoder!!.encode(user.password!!)
        user.enabled = true
        val userRole = roleRepository!!.findByRole("ADMIN")
        user.roles = HashSet(Arrays.asList(userRole))
        userRepository!!.save(user)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {

        val user = userRepository!!.findByEmail(email)
        if (user != null) {
            val authorities = getUserAuthority(user.roles!!)
            return buildUserForAuthentication(user, authorities)
        } else {
            throw UsernameNotFoundException("username not found")
        }
    }

    private fun getUserAuthority(userRoles: Set<Role>): List<GrantedAuthority> {
        val roles = HashSet<GrantedAuthority>()
        userRoles.forEach { role -> roles.add(SimpleGrantedAuthority(role.role)) }

        return ArrayList(roles)
    }

    private fun buildUserForAuthentication(user: User, authorities: List<GrantedAuthority>): UserDetails {
        return org.springframework.security.core.userdetails.User(user.email!!, user.password!!, authorities)
    }

}
