package ru.landgrafhomyak.object_storage

interface Connection {
    suspend fun startTransaction(): TransactionContext
}