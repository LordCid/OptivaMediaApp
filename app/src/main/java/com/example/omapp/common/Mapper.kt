package com.example.omapp.common

interface Mapper<T, R> {
    fun map(input: R): T
}