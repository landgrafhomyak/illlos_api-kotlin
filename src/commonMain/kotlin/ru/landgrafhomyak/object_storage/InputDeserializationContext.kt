package ru.landgrafhomyak.object_storage


@OptIn(ExperimentalUnsignedTypes::class)
interface InputDeserializationContext {
    fun parseULong(offset: UInt): ULong
    fun parsePtr(offset: UInt): Ptr
    fun loadBlob(offset: UInt, dst: UByteArray, size: UInt = dst.size.toUInt(), dstOffset: UInt = 0u)

    suspend fun loadString(address: Ptr): String
    suspend fun <T : Any> loadObject(serializer: Deserializer<T>, address: Ptr): T?
    suspend fun <T : Any> loadList(serializer: Deserializer<T>, address: Ptr): StoredCollection<T>?
}