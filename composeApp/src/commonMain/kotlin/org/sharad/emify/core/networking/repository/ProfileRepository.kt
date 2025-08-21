package org.sharad.emify.core.networking.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.sharad.emify.core.networking.NetworkError
import org.sharad.emify.core.networking.NetworkResponse
import org.sharad.emify.core.util.Prefs
import org.sharad.emify.core.util.logDebug
import org.sharad.emify.core.util.urlBuilder
import org.sharad.emify.features.login.data.OnBoardingBody
import org.sharad.emify.features.login.data.ProfileInfoDto

class ProfileRepository(
    private val httpClient: HttpClient,
    private val prefs: Prefs
) {

    suspend fun getProfileInfo(userId: String): NetworkResponse<ProfileInfoDto, NetworkError> {
        val primaryToken = prefs.getPrimaryToken()
        val secondaryToken = prefs.getRefreshToken()

        if (primaryToken == null || secondaryToken == null) {
            logDebug("Profile Info", "No token found. Unauthorized.")
            return NetworkResponse.Error(NetworkError.UNAUTHORIZED)
        }

        logDebug("Profile Info", "Fetching profile for userId: $userId")
        logDebug("Profile Info", "Primary Token: $primaryToken")
        logDebug("Profile Info", "Secondary Token: $secondaryToken")

        val response = try {
            httpClient.get(urlBuilder("v1/user/$userId/profile")) {
                bearerAuth(primaryToken)
            }
        } catch (e: UnresolvedAddressException) {
            logDebug("Profile Info", "No internet connection.")
            return NetworkResponse.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            logDebug("Profile Info", "Serialization error: ${e.toString()}")
            return NetworkResponse.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            logDebug("Profile Info", "Unknown error: ${e.toString()}")
            return NetworkResponse.Error(NetworkError.UNKNOWN)
        }

        logDebug("Profile Info", "Response Status: ${response.status}")
        logDebug("Profile Info", "Response Body: ${response.bodyAsText()}")

        return when (response.status.value) {
            in 200..299 -> NetworkResponse.Success(response.body())
            401 -> NetworkResponse.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResponse.Error(NetworkError.CONFLICT)
            408 -> NetworkResponse.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResponse.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResponse.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResponse.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun onBoardUser(
        firstName: String,
        lastName: String?,
        userId: String
    ): NetworkResponse<Boolean, NetworkError> {
        val primaryToken = prefs.getPrimaryToken()
        val secondaryToken = prefs.getRefreshToken()

        if (primaryToken == null || secondaryToken == null) {
            logDebug("OnBoard User", "No token found. Unauthorized.")
            return NetworkResponse.Error(NetworkError.UNAUTHORIZED)
        }

        logDebug("OnBoard User", "Onboarding userId: $userId")
        logDebug("OnBoard User", "First Name: $firstName, Last Name: $lastName")
        logDebug("OnBoard User", "Primary Token: $primaryToken")

        val response = try {
            httpClient.patch(urlBuilder("v1/user/$userId/profile")) {
                contentType(ContentType.Application.Json)
                setBody(
                    OnBoardingBody(
                        first_name = firstName,
                        last_name = lastName
                    )
                )
                bearerAuth(primaryToken)
            }
        } catch (e: UnresolvedAddressException) {
            logDebug("OnBoard User", "No internet connection.")
            return NetworkResponse.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            logDebug("OnBoard User", "Serialization error: ${e.toString()}")
            return NetworkResponse.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            logDebug("OnBoard User", "Unknown error: ${e.toString()}")
            return NetworkResponse.Error(NetworkError.UNKNOWN)
        }

        logDebug("OnBoard User", "Response Status: ${response.status}")
        logDebug("OnBoard User", "Response Body: ${response.bodyAsText()}")

        return when (response.status.value) {
            in 200..299 -> NetworkResponse.Success(true)
            401 -> NetworkResponse.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResponse.Error(NetworkError.CONFLICT)
            408 -> NetworkResponse.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResponse.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResponse.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResponse.Error(NetworkError.UNKNOWN)
        }
    }
}
