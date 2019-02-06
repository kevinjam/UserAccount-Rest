package com.thinkdevs.security.repositories

import com.thinkdevs.security.model.Role

interface RoleRepository {
    fun findByRole(role: String): Role
}