package ru.landgrafhomyak.object_storage

import kotlin.jvm.JvmInline

@JvmInline
value class StoredCollection<@Suppress("unused") T : Any> internal constructor(internal val page: Any)