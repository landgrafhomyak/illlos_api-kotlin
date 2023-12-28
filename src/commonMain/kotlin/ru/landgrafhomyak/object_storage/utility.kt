package ru.landgrafhomyak.object_storage

typealias TypeId = UByte

@OptIn(ExperimentalUnsignedTypes::class)
internal fun parseUInt(src: UByteArray, offset: UInt): ULong {
    @Suppress("NAME_SHADOWING")
    val offset = offset.toInt()
    return src[offset].toULong().shl(56) or
            src[offset + 1].toULong().shl(48) or
            src[offset + 2].toULong().shl(40) or
            src[offset + 3].toULong().shl(32) or
            src[offset + 4].toULong().shl(24) or
            src[offset + 5].toULong().shl(16) or
            src[offset + 6].toULong().shl(8) or
            src[offset + 7].toULong()
}

@OptIn(ExperimentalUnsignedTypes::class)
internal fun dumpULong(src: UByteArray, offset: UInt, value: ULong) {
    @Suppress("NAME_SHADOWING")
    val offset = offset.toInt()
    src[offset] = value.shr(56).toUByte()
    src[offset + 1] = value.shr(48).toUByte()
    src[offset + 2] = value.shr(40).toUByte()
    src[offset + 3] = value.shr(32).toUByte()
    src[offset + 4] = value.shr(24).toUByte()
    src[offset + 5] = value.shr(16).toUByte()
    src[offset + 6] = value.shr(8).toUByte()
    src[offset + 7] = value.toUByte()
}

suspend inline fun OutputCollectionSerializationContext.addElement(creator: (OutputObjectSerializationContext) -> Unit) {
    val ctx = this.addElement()
    creator(ctx)
}

inline fun OutputCollectionSerializationContext.editElement(obj: StoredObject, editor: (OutputObjectSerializationContext) -> Unit) {
    val ctx = this.editElement(obj)
    editor(ctx)
}

inline fun <T:Any> OutputObjectSerializationContext.editCollection(collection: StoredCollection<T>, elementDescriptor: ObjectDescriptor<T>,  editor: (OutputCollectionSerializationContext) -> Unit) {
    val ctx = this.editCollection(collection, elementDescriptor)
    editor(ctx)
}