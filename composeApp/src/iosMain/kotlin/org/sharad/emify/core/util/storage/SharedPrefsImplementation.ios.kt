package org.sharad.emify.core.util.storage

import platform.Foundation.NSUserDefaults

actual class SharedPrefsImplementation actual constructor(context: Any?) : SharedPrefsInterface {

    private var userDefaults = NSUserDefaults.standardUserDefaults

    actual override fun setString(key: String, value: String?) = userDefaults.setObject(value, key)

    actual override fun getString(key: String): String? = userDefaults.stringForKey(key)

    actual override fun setInt(key: String, value: Int) = userDefaults.setInteger(value.toLong(), key)

    actual override fun getInt(key: String): Int? = if (userDefaults.objectForKey(key) != null) {
        userDefaults.integerForKey(key).toInt()
    } else {
        null
    }

    actual override fun setLong(key: String, value: Long) = userDefaults.setInteger(value, key)

    actual override fun getLong(key: String): Long? = if (userDefaults.objectForKey(key) != null) {
        userDefaults.integerForKey(key)
    } else {
        null
    }

    actual override fun setFloat(key: String, value: Float) = userDefaults.setFloat(value, key)

    actual override fun getFloat(key: String): Float? = if (userDefaults.objectForKey(key) != null) {
        userDefaults.floatForKey(key)
    } else {
        null
    }

    actual override fun setDouble(key: String, value: Double) = userDefaults.setDouble(value, key)

    actual override fun getDouble(key: String): Double? = if (userDefaults.objectForKey(key) != null) {
        userDefaults.doubleForKey(key)
    } else {
        null
    }

    actual override fun setBoolean(key: String, value: Boolean) = userDefaults.setBool(value, key)

    actual override fun getBoolean(key: String): Boolean? = if (userDefaults.objectForKey(key) != null) {
        userDefaults.boolForKey(key)
    } else {
        null
    }

    actual override fun contains(key: String): Boolean = userDefaults.objectForKey(key) != null

    actual override fun remove(key: String) = userDefaults.removeObjectForKey(key)

    actual override fun clear()  {
        return userDefaults.dictionaryRepresentation().keys.forEach { key ->
            userDefaults.removeObjectForKey(key as String)
        }
    }
}