package org.sharad.emify.core.networking.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.SerializationException
import org.sharad.emify.core.networking.NetworkError
import org.sharad.emify.core.networking.NetworkResponse
import org.sharad.emify.core.util.Prefs
import org.sharad.emify.core.util.logDebug
import org.sharad.emify.core.util.storage.SharedPrefsInterface
import org.sharad.emify.core.util.urlBuilder
import org.sharad.emify.features.login.data.AuthInitiateBody
import org.sharad.emify.features.login.data.AuthInitiateDto
import org.sharad.emify.features.login.data.OtpValidationBody
import org.sharad.emify.features.login.data.OtpValidationSuccessDto

class LoginRepository(private val httpClient: HttpClient, private val prefs: Prefs) {

    suspend fun initiateAuth(number: String): NetworkResponse<AuthInitiateDto, NetworkError> {
        val response= try {
            httpClient.post(urlBuilder("v1/auth/initiate")) {
                contentType(ContentType.Application.Json)
                setBody(AuthInitiateBody(number))
            }
        }catch(e: UnresolvedAddressException) {
            return NetworkResponse.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return NetworkResponse.Error(NetworkError.SERIALIZATION)
        }catch (e: Exception){
            return NetworkResponse.Error(NetworkError.UNKNOWN)
        }

        logDebug("initiateAuth", response.toString())

        return when(response.status.value){
            in 200..299->{
                NetworkResponse.Success(response.body())
            }
            401 -> NetworkResponse.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResponse.Error(NetworkError.CONFLICT)
            408 -> NetworkResponse.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResponse.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResponse.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResponse.Error(NetworkError.UNKNOWN)
        }
    }


    suspend fun validateOtp(otp: String, userId: String): NetworkResponse<OtpValidationSuccessDto, NetworkError> {
        val body=OtpValidationBody(
            code = otp,
            fb_installation_id = prefs.getFCMInstallationId(),
            fcm_token = prefs.getFCMToken(),
            user_id = userId,
            device_name = "android"
        )
        logDebug("validateOtp", body.toString())
        val response= try {
            httpClient.post(urlBuilder("v1/auth/validate-otp")) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        }catch(e: UnresolvedAddressException) {
            return NetworkResponse.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return NetworkResponse.Error(NetworkError.SERIALIZATION)
        }catch (e: Exception){
            return NetworkResponse.Error(NetworkError.UNKNOWN)
        }

        logDebug("initiateAuth", response.toString())

        return when(response.status.value){
            in 200..299->{
                NetworkResponse.Success(response.body())
            }
            400-> NetworkResponse.Error(NetworkError.INVALID_REQUEST)
            403-> NetworkResponse.Error(NetworkError.INVALID_OTP)
            401 -> NetworkResponse.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResponse.Error(NetworkError.CONFLICT)
            408 -> NetworkResponse.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResponse.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResponse.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResponse.Error(NetworkError.UNKNOWN)
        }
    }

}