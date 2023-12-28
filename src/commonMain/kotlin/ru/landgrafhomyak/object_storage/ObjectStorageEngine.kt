package ru.landgrafhomyak.object_storage

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

abstract class ObjectStorageEngine(protected val bitsForPageSize: UInt, firstPage: RawInputPage) {
    protected abstract suspend fun getPage(pageNo: ULong): RawInputPage?


    private var isConnected = false

    suspend fun connect() {
        if (this.isConnected)
            throw IllegalStateException("Already connected")
    }

    private fun assertConnected() {
        if (!this.isConnected)
            throw IllegalStateException("Not connected")
    }


    private inner class PagesList(firstPage: Page.Header) {
        private val refs = arrayOf<Page>(firstPage)
        private suspend fun load(pgNo: ULong) {
            val rawPage = this@ObjectStorageEngine.getPage(pgNo)


        }
    }

    suspend fun load(receiver: LoaderReceiver) {
        this.assertConnected()
        coroutineScope {
            launch { }
        }
    }

    @Suppress("FunctionName")
    @PublishedApi
    internal fun <T : Any> _editObject(descriptor: ObjectDescriptor<T>, obj: StoredObject): OutputObjectSerializationContext {

    }

    @Suppress("FunctionName")
    @PublishedApi
    internal fun <T : Any> _editCollection(descriptor: ObjectDescriptor<T>, coll: StoredCollection<T>): OutputCollectionSerializationContext {
    }

    @Suppress("FunctionName")
    @PublishedApi
    internal suspend fun _commit(ctx: OutputObjectSerializationContext) {
    }

    @Suppress("FunctionName")
    @PublishedApi
    internal suspend fun _commit(ctx: OutputCollectionSerializationContext) {
    }

    @Suppress("FunctionName")
    @PublishedApi
    internal suspend fun _rollback(ctx: OutputObjectSerializationContext) {
    }

    @Suppress("FunctionName")
    @PublishedApi
    internal suspend fun _rollback(ctx: OutputCollectionSerializationContext) {
    }

    suspend inline fun <T : Any> editObject(descriptor: ObjectDescriptor<T>, obj: StoredObject, editor: (OutputObjectSerializationContext) -> Unit) {
        val ctx = this._editObject(descriptor, obj)
        var exception: Throwable? = null
        try {
            editor(ctx)
        } catch (e: Throwable) {
            exception = e
        }
        if (exception == null) {
            this._commit(ctx)
        } else {
            try {
                this._rollback(ctx)
            } catch (e: Throwable) {
                e.addSuppressed(exception)
                throw e
            }
        }
    }

    suspend inline fun <T : Any> editCollection(descriptor: ObjectDescriptor<T>, coll: StoredCollection<T>, editor: (OutputCollectionSerializationContext) -> Unit) {
        val ctx = this._editCollection(descriptor, coll)
        var exception: Throwable? = null
        try {
            editor(ctx)
        } catch (e: Throwable) {
            exception = e
        }
        if (exception == null) {
            this._commit(ctx)
        } else {
            try {
                this._rollback(ctx)
            } catch (e: Throwable) {
                e.addSuppressed(exception)
                throw e
            }
        }
    }

    open suspend fun disconnect() {
        this.assertConnected()
        this.isConnected = false
    }
}