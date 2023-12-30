package ru.landgrafhomyak.illlos

interface Connection {
    suspend fun startTransaction(): TransactionContext
}