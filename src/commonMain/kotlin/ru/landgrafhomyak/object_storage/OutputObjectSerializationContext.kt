package ru.landgrafhomyak.object_storage

interface OutputObjectSerializationContext<@Suppress("unused") T : Any> {
    fun saveValue(fieldNo: UInt, value: ULong)
    fun <E : Any> saveObjectReference(fieldNo: UInt, descriptor: ObjectDescriptor<E>, reference: ObjectReference<E>)
    fun <E : Any> saveCollectionReference(fieldNo: UInt, collectionElementdescriptor: ObjectDescriptor<E>, reference: CollectionReference<E>)
    fun finishEditing()
}