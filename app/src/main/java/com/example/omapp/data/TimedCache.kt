package com.example.omapp.data

import androidx.annotation.VisibleForTesting
import com.example.omapp.domain.model.Movie
import java.util.*

const val CACHE_LIFE_TIME = 30000L



class TimedCache {

    private val lifetime: Long = CACHE_LIFE_TIME

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var cache: Pair<MutableList<Movie>, Date> = Pair(mutableListOf(), Date())

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var currentDate: () -> Date = { Date() }

    fun get(): List<Movie> {
        return if (currentDate().time <= cache.second.time)
            cache.first
        else
            emptyList()
    }

    fun set(value: MutableList<Movie>) {
        val validUntil = Date(currentDate().time + lifetime)
        if(cache.first.isEmpty()){
            cache = Pair(value, validUntil)
        } else {
            cache.first.addAll(value)
        }
    }

    fun invalidate() {
        cache = Pair(mutableListOf(), Date())
    }

}