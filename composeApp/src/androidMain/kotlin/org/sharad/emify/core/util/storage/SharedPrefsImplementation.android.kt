package org.sharad.emify.core.util.storage

import android.content.Context
import androidx.core.content.edit

actual class SharedPrefsImplementation actual constructor(context: Any?) : SharedPrefsInterface {

    val prefs= (context as Context).getSharedPreferences("EmiFy", Context.MODE_PRIVATE)

    actual override fun setString(key: String, value: String?) {
        prefs.edit { putString(key, value) }
    }

    actual override fun getString(key: String): String? {
       return prefs.getString(key, null)
    }

    actual override fun setInt(key: String, value: Int) {
        prefs.edit { putInt(key, value) }
    }

    actual override fun getInt(key: String): Int? {
        return prefs.getInt(key, -1)
    }

    actual override fun setLong(key: String, value: Long) {
        prefs.edit { putLong(key, value) }
    }

    actual override fun getLong(key: String): Long? {
       return prefs.getLong(key, -1)
    }

    actual override fun setFloat(key: String, value: Float) {
        prefs.edit { putFloat(key, value) }
    }

    actual override fun getFloat(key: String): Float? {
        return prefs.getFloat(key, -1f)
    }

    actual override fun setDouble(key: String, value: Double) {
        prefs.edit { putString(key, value.toString())}
    }

    actual override fun getDouble(key: String): Double? {
        return prefs.getString(key, null)?.toDouble()
    }

    actual override fun setBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    actual override fun getBoolean(key: String): Boolean {
       return prefs.getBoolean(key, false)
    }

    actual override fun contains(key: String): Boolean {
        return prefs.contains(key)
    }

    actual override fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    actual override fun clear() {
        prefs.edit { clear() }
    }
}