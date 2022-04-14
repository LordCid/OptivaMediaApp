package com.example.omapp.common

interface Mapper<T, DTO> {
    fun mapToDomainModel(dto: DTO): T
}