package ru.landgrafhomyak.object_storage

interface LoaderReceiver {
    suspend fun parseHeader(ctx: InputDeserializationContext)

    fun getDeserializer(typeId: TypeId): Deserializer<Any>?
}