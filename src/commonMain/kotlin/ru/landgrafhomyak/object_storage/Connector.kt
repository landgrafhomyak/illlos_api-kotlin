package ru.landgrafhomyak.object_storage

interface Connector {
    suspend fun connectAndLoad(receiver: LoaderReceiver)
}