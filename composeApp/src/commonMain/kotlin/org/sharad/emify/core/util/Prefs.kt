package org.sharad.emify.core.util

import org.sharad.emify.core.util.storage.SharedPrefsInterface

class Prefs(private val SharedPrefs: SharedPrefsInterface) {

    fun getLoginStatus():Boolean{
        return SharedPrefs.getBoolean("LOGIN_STATUS") ?: false
    }

    fun setLoginStatus(data: Boolean){
        SharedPrefs.setBoolean("LOGIN_STATUS", data)
    }

    fun setUserId(data: String?){
        SharedPrefs.setString("USER_ID", data)
    }

    fun getUserId(): String?{
        return SharedPrefs.getString("USER_ID")
    }

    fun setFCMToken(data: String){
        SharedPrefs.setString("FCM_TOKEN", data)
    }

    fun getFCMToken(): String{
        return SharedPrefs.getString("FCM_TOKEN") ?: "9876543210"
    }

    fun setFCMInstallationId(data: String){
        SharedPrefs.setString("FCM_Installation_Id", data)
    }

    fun getFCMInstallationId(): String{
        return SharedPrefs.getString("FCM_Installation_Id") ?: "9876543210"
    }

    fun setPrimaryToken(data: String?){
        SharedPrefs.setString("PRIMARY_TOKEN", data)
    }
    fun getPrimaryToken(): String?{
        return SharedPrefs.getString("PRIMARY_TOKEN")
    }
    fun setRefreshToken(data: String?){
        SharedPrefs.setString("REFRESH_TOKEN", data)
    }
    fun getRefreshToken(): String?{
        return SharedPrefs.getString("REFRESH_TOKEN")
    }

}