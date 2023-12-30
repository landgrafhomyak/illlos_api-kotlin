@file:OptIn(ExperimentalContracts::class)

package ru.landgrafhomyak.illlos

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@Suppress("SpellCheckingInspection")
@PublishedApi
internal inline fun <T : Any> autoFinishableImpl(obj: T, block: (T) -> Unit, onError: () -> Unit, onSuccess: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        callsInPlace(onError, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
    }

    var exception: Throwable? = null
    try {
        block(obj)
        onSuccess()
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        try {
            onError()
        } catch (e: Throwable) {
            if (exception == null)
                throw e
            else
                exception.addSuppressed(e)
        }
    }
}

suspend inline fun Connection.startTransaction(
    transactionScope: (TransactionContext) -> Unit
) {
    contract {
        callsInPlace(transactionScope, InvocationKind.EXACTLY_ONCE)
    }
    val transaction = this.startTransaction()
    autoFinishableImpl(
        obj = transaction,
        block = transactionScope,
        onError = { transaction.rollback() },
        onSuccess = { transaction.commit() }
    )
}

suspend inline fun <T : Any> TransactionContext.createObject(
    descriptor: ObjectDescriptor<T>,
    serializer: (OutputObjectSerializationContext<T>) -> Unit
) {
    contract {
        callsInPlace(serializer, InvocationKind.EXACTLY_ONCE)
    }
    val context = this.createObject(descriptor)
    autoFinishableImpl(
        obj = context,
        block = serializer,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
}

suspend inline fun <T : Any> TransactionContext.editObject(
    descriptor: ObjectDescriptor<T>,
    reference: ObjectReference<T>,
    serializer: (OutputObjectSerializationContext<T>) -> Unit
) {
    contract {
        callsInPlace(serializer, InvocationKind.EXACTLY_ONCE)
    }
    val context = this.editObject(descriptor, reference)
    autoFinishableImpl(
        obj = context,
        block = serializer,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
}

suspend inline fun <T : Any> TransactionContext.createCollection(
    elementDescriptor: ObjectDescriptor<T>,
    initializer: (OutputCollectionSerializationContext<T>) -> Unit
): CollectionReference<T> {
    contract {
        callsInPlace(initializer, InvocationKind.EXACTLY_ONCE)
    }
    val reference = this.createCollection(elementDescriptor)
    val context = this.editCollection(reference, elementDescriptor)
    autoFinishableImpl(
        obj = context,
        block = initializer,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
    return reference
}

suspend inline fun <T : Any> TransactionContext.editCollection(
    reference: CollectionReference<T>,
    elementDescriptor: ObjectDescriptor<T>,
    editor: (OutputCollectionSerializationContext<T>) -> Unit
) {
    contract {
        callsInPlace(editor, InvocationKind.EXACTLY_ONCE)
    }
    val context = this.editCollection(reference, elementDescriptor)
    autoFinishableImpl(
        obj = context,
        block = editor,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
}

suspend inline fun <T : Any> OutputCollectionSerializationContext<T>.addElement(
    initializer: (OutputObjectSerializationContext<T>) -> Unit
) {
    contract {
        callsInPlace(initializer, InvocationKind.EXACTLY_ONCE)
    }
    val context = this.addElement()
    autoFinishableImpl(
        obj = context,
        block = initializer,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
}

suspend inline fun <T : Any> OutputCollectionSerializationContext<T>.editElement(
    reference: ObjectReference<T>,
    editor: (OutputObjectSerializationContext<T>) -> Unit
) {
    contract {
        callsInPlace(editor, InvocationKind.EXACTLY_ONCE)
    }
    val context = this.editElement(reference)
    autoFinishableImpl(
        obj = context,
        block = editor,
        onError = { context.finishEditing() },
        onSuccess = { context.finishEditing() }
    )
}
