package ru.landgrafhomyak.illlos

interface TransactionContext {
    suspend fun <T : Any> createObject(descriptor: ObjectDescriptor<T>): OutputObjectSerializationContext<T>
    suspend fun <T : Any> editObject(descriptor: ObjectDescriptor<T>, reference: ObjectReference<T>): OutputObjectSerializationContext<T>
    suspend fun deleteObject(reference: ObjectReference<Any>)
    suspend fun <T : Any> createCollection(elementDescriptor: ObjectDescriptor<T>): CollectionReference<T>
    suspend fun <T : Any> editCollection(reference: CollectionReference<T>, elementDescriptor: ObjectDescriptor<T>): OutputCollectionSerializationContext<T>
    suspend fun deleteCollection(reference: CollectionReference<Any>)
    suspend fun rollback()
    suspend fun commit()
}