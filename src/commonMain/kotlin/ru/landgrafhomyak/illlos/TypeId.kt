package ru.landgrafhomyak.illlos

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
value class TypeId private constructor(@Suppress("PropertyName") val _value: UShort) {
    companion object {
        @JvmStatic
        fun fromUShort(value: UShort) = TypeId(value)
    }
}