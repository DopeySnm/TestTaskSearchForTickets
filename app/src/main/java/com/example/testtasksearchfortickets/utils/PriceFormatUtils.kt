package com.example.testtasksearchfortickets.utils

object PriceFormatUtils {
    fun priceFormat(price: String): String {
        return price.reversed()
            .chunked(3)
            .joinToString(" ")
            .reversed()
    }
}
