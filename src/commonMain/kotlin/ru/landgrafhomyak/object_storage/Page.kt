package ru.landgrafhomyak.object_storage

@Suppress("RemoveRedundantQualifierName")
@OptIn(ExperimentalUnsignedTypes::class)
internal sealed class Page(private val raw: RawOutputPage, private val headerSize: UInt) {
    class Header private constructor(raw: RawOutputPage, headerSize: UInt) : Page(raw, headerSize) {

    }

    class Objects private constructor(
        raw: RawOutputPage,
        private val prevSame: Page.Objects?,
        private val nextSame: Page.Objects?,
        private val descriptor: ObjectDescriptor<*>,
        private val freeCells: ArrayList<UInt>
    ) : Page(raw, 8u) {

    }
}