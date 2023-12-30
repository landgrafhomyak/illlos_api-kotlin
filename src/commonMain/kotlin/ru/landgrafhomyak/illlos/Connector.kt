package ru.landgrafhomyak.illlos

interface Connector {
    suspend fun connectAndLoad(receiver: LoaderReceiver)
}