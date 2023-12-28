package ru.landgrafhomyak.object_storage

import kotlin.jvm.JvmInline

@JvmInline
value class Ptr internal constructor(internal val value: ULong)