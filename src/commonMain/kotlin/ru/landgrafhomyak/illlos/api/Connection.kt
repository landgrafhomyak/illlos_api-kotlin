package ru.landgrafhomyak.illlos.api

interface Connection {
    suspend fun startTransaction(): TransactionContext
}