package com.hasom.firstkotlinapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GlobalUtils {

    companion object {
        fun dateFormat(format :String):String {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern(format)
            val formatted = current.format(formatter)

            return formatted
        }
    }
}
