package org.sharad.emify.core.util.storage

expect class SharedPrefsImplementation(context: Any?=null): SharedPrefsInterface {
    override fun setString(key: String, value: String?)
    override fun getString(key: String): String?
    override fun setInt(key: String, value: Int)
    override fun getInt(key: String): Int?
    override fun setLong(key: String, value: Long)
    override fun getLong(key: String): Long?
    override fun setFloat(key: String, value: Float)
    override fun getFloat(key: String): Float?
    override fun setDouble(key: String, value: Double)
    override fun getDouble(key: String): Double?
    override fun setBoolean(key: String, value: Boolean)
    override fun getBoolean(key: String): Boolean
    override fun contains(key: String): Boolean
    override fun remove(key: String)
    override fun clear()
}