package ru.landgrafhomyak.illlos.api


interface OutputCollectionSerializationContext<T : Any> {
    suspend fun addElement(): OutputObjectSerializationContext<T>
    suspend fun editElement(reference: ObjectReference<T>): OutputObjectSerializationContext<T>
    suspend fun removeElement(reference: ObjectReference<T>)
    fun finishEditing()
}