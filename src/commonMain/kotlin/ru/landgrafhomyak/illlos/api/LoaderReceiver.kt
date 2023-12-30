package ru.landgrafhomyak.illlos.api

interface LoaderReceiver {
    suspend fun parseHeader(ctx: InputDeserializationContext)

    fun getDeserializer(typeId: TypeId): Deserializer<Any>?
}