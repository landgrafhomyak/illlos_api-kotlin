package ru.landgrafhomyak.object_storage

import kotlin.jvm.JvmInline

@JvmInline
value class StoredObject internal constructor(internal val address: ULong)
