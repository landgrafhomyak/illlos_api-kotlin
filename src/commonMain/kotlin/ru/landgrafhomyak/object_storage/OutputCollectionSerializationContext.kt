package ru.landgrafhomyak.object_storage


interface OutputCollectionSerializationContext {
    fun  editElement(address: StoredObject): OutputObjectSerializationContext
    suspend fun addElement(): OutputObjectSerializationContext
    fun  removeElement(address: StoredObject)
}