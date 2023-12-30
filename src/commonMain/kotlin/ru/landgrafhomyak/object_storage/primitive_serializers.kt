package ru.landgrafhomyak.object_storage

@OptIn(ExperimentalUnsignedTypes::class)
fun parseUInt(src: UByteArray, offset: UInt): ULong {
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

fun parseUInt(b0: UByte, b1: UByte, b2: UByte, b3: UByte, b4: UByte, b5: UByte, b6: UByte, b7: UByte): ULong {
    return b0.toULong().shl(56) or
            b1.toULong().shl(48) or
            b2.toULong().shl(40) or
            b3.toULong().shl(32) or
            b4.toULong().shl(24) or
            b5.toULong().shl(16) or
            b6.toULong().shl(8) or
            b7.toULong()
}

@OptIn(ExperimentalUnsignedTypes::class)
fun dumpULong(src: UByteArray, offset: UInt, value: ULong) {
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