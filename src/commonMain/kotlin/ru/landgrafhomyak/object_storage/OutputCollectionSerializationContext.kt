package ru.landgrafhomyak.object_storage


interface OutputCollectionSerializationContext<T : Any> {
    suspend fun addElement(): OutputObjectSerializationContext<T>
    suspend fun editElement(reference: ObjectReference<T>): OutputObjectSerializationContext<T>
    suspend fun removeElement(reference: ObjectReference<T>)
    fun finishEditing()
}