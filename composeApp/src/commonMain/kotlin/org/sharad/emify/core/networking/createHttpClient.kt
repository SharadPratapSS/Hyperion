package org.sharad.emify.core.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpSendInterceptor
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient{
    return HttpClient(engine) {
        install(Logging){
            level= LogLevel.ALL
        }
        install(ContentNegotiation){
            json(
                json = Json {
                    ignoreUnknownKeys=true
                }
            )
        }
    }
}