package com.example.omapp.data

import androidx.annotation.VisibleForTesting
import java.util.*

class TimedCache(
    private val lifetime: Long = 3 * 60 * 1000
) {

    private var cache: MutableMap<String, Pair<Any, Date>> = mutableMapOf()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var currentDate: () -> Date = { Date() }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String): T? {
        cache[key]?.let { (value, date) ->
            return if (currentDate().time <= date.time) {
                        value as T
                    } else null
        }
        return null
    }

    operator fun set(key: String, value: Any?) {
        val validUntil = Date(currentDate().time + lifetime)
        if (value != null) {
            cache[key] = Pair(value, validUntil)
        } else {
            cache.remove(key)
        }
    }

    fun invalidate() {
        cache = mutableMapOf()
    }

    fun removeKeysByPattern(regex: Regex) {
        cache.filterKeys { it.contains(regex) }.keys.forEach { cache.remove(it) }
    }
}