//package org.sharad.emify.core.networking
//
//import io.ktor.client.*
//import io.ktor.client.call.*
//import io.ktor.client.plugins.*
//import io.ktor.client.plugins.api.*
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//import io.ktor.util.AttributeKey
//import kotlinx.coroutines.sync.Mutex
//import kotlinx.coroutines.sync.withLock
//import org.sharad.emify.core.util.Prefs
//
//class AuthTokenPlugin(
//    val prefs: Prefs,
//    val authApi: AuthApi
//) {
//
//    private val mutex = Mutex()
//
//    companion object Plugin : HttpClientPlugin<Config, AuthTokenPlugin> {
//        override val key: AttributeKey<AuthTokenPlugin> = AttributeKey("AuthTokenPlugin")
//
//        override fun prepare(block: Config.() -> Unit): AuthTokenPlugin {
//            return AuthTokenPlugin(Config().prefs!!, Config().authApi!!)
//        }
//
//        override fun install(plugin: AuthTokenPlugin, scope: HttpClient) {
//
//            scope.receivePipeline.intercept(HttpReceivePipeline.After) { response ->
//                if (response is HttpResponse && response.status == HttpStatusCode.Unauthorized) {
//
//                    plugin.mutex.withLock {
//
//                        // Refresh token logic
//                        val refreshToken = plugin.prefs.getRefreshToken()
//                        if (refreshToken.isNullOrBlank()) throw ResponseException(response, "Refresh token missing")
//
//                        val newTokenResponse = plugin.authApi.refreshToken(refreshToken)
//                        plugin.prefs.setPrimaryToken(newTokenResponse.primary_token)
//                        plugin.prefs.setRefreshToken(newTokenResponse.refresh_token)
//
//                        // Retry the request manually
//                        val original = context.request
//                        val newResponse: HttpResponse = scope.request(original.url) {
//                            method = original.method
//                            headers {
//                                appendAll(original.headers)
//                                set("Authorization", "Bearer ${plugin.prefs.getPrimaryToken()}")
//                            }
//                            setBody(original.body)
//                        }
//
//                        proceedWith(newResponse)
//                    }
//                }
//            }
//
//        }
//    }
//
//    class Config {
//        var prefs: Prefs? = null
//        var authApi: AuthApi? = null
//    }
//}
//
//
//interface AuthApi{
//    fun refreshToken(refreshToken: String)
//}