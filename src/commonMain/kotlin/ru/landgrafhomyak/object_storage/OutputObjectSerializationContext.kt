package ru.landgrafhomyak.object_storage


@OptIn(ExperimentalUnsignedTypes::class)
interface OutputObjectSerializationContext {
    fun saveULong(offset: UInt, value: ULong)
    fun savePtr(offset: UInt, value: Ptr)
    fun savePtr(offset: UInt, value: StoredCollection<*>)
    fun saveBlob(offset: UInt, dst: UByteArray, size: UInt = dst.size.toUInt(), dstOffset: UInt = 0u)
    suspend fun allocString(raw: String): Ptr
    suspend fun <T : Any> createCollection(elementDescriptor: ObjectDescriptor<T>): StoredCollection<T>
    fun <T : Any> editCollection(collection: StoredCollection<T>, elementDescriptor: ObjectDescriptor<T>): OutputCollectionSerializationContext
}