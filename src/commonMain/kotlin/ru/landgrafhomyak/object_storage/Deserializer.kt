package ru.landgrafhomyak.object_storage


abstract class Deserializer<T : Any>(typeId: TypeId, objSize: UInt) : ObjectDescriptor<T>(typeId, objSize) {
    abstract fun deserialize(input: InputDeserializationContext): T
    open fun receive(objId: StoredObject, obj: T) {}
}