package com.example.jocasta.utility

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * A [Regex] pattern that matches any number of digits within a string.
 */
private val DIGIT_PATTERN: Regex = "\\d+".toRegex()

/**
 * Deserialize a JSON string as an [Int] with null safety by resolving a null-value as -1.
 */
class DeserializeInt : JsonDeserializer<Int> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Int {
        val result: Int? = json?.asString?.let { it ->
            DIGIT_PATTERN.find(it)?.value?.toIntOrNull()
        }

        return result ?: -1
    }
}

/**
 * Deserialize a JSON string array as a [List]<[Int]> with null safety by resolving a null-value as
 * -1 and an invalid input array as an empty list.
 */
class DeserializeIntList : JsonDeserializer<List<Int>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Int> {
        val result: List<Int>? = json?.asJsonArray?.map { element ->
            element.asString.let { it ->
                DIGIT_PATTERN.find(it)?.value?.toInt() ?: -1
            }
        }

        return result ?: emptyList()
    }
}