package ru.landgrafhomyak.illlos

interface LoaderReceiver {
    suspend fun parseHeader(ctx: InputDeserializationContext)

    fun getDeserializer(typeId: TypeId): Deserializer<Any>?
}