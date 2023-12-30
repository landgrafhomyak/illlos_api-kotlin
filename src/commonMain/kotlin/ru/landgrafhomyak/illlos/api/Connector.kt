package ru.landgrafhomyak.illlos.api

interface Connector {
    suspend fun connectAndLoad(receiver: LoaderReceiver)
}