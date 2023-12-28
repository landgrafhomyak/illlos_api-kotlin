package ru.landgrafhomyak.object_storage

@OptIn(ExperimentalUnsignedTypes::class)
interface RawOutputPage {
    suspend fun startWriting()
    suspend fun finishWriting()

    fun saveULong(offset: UInt, value: ULong)
    fun saveBlob(offset: UInt, src: UByteArray, size: UInt = src.size.toUInt(), srcOffset: UInt = 0u)
}