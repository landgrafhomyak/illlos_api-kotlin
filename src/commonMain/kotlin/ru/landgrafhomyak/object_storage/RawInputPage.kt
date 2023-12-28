package ru.landgrafhomyak.object_storage

@OptIn(ExperimentalUnsignedTypes::class)
interface RawInputPage {
    fun loadULong(offset: UInt): ULong

    fun loadBlob(offset: UInt, dst: UByteArray, size: UInt = dst.size.toUInt(), dstOffset: UInt = 0u)

    fun finishReading(): RawOutputPage
}