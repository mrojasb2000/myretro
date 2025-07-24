package com.bramwork.myretro.persistence

import org.springframework.stereotype.Repository

// Repository interface for generic CRUD operations
@Repository
interface BaseRepository<D, ID>{
    fun save(domain: D): D
    fun findById(id: ID): D
    fun findAll(): Iterable<D>
    fun delete(id: ID)
}