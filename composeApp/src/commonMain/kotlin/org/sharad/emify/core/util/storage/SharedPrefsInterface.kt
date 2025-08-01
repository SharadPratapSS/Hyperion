package org.sharad.emify.core.util.storage

interface SharedPrefsInterface {

    /**
     * Store a string value
     */
    fun setString(key: String, value: String)

    /**
     * Retrieve a string value
     * @return The stored value or null if not found
     */
    fun getString(key: String): String?

    /**
     * Store an integer value
     */
    fun setInt(key: String, value: Int)

    /**
     * Retrieve an integer value
     * @return The stored value or null if not found
     */
    fun getInt(key: String): Int?

    /**
     * Store a long value
     */
    fun setLong(key: String, value: Long)

    /**
     * Retrieve a long value
     * @return The stored value or null if not found
     */
    fun getLong(key: String): Long?

    /**
     * Store a float value
     */
    fun setFloat(key: String, value: Float)

    /**
     * Retrieve a float value
     * @return The stored value or null if not found
     */
    fun getFloat(key: String): Float?

    /**
     * Store a double value
     */
    fun setDouble(key: String, value: Double)

    /**
     * Retrieve a double value
     * @return The stored value or null if not found
     */
    fun getDouble(key: String): Double?

    /**
     * Store a boolean value
     */
    fun setBoolean(key: String, value: Boolean)

    /**
     * Retrieve a boolean value
     * @return The stored value or null if not found
     */
    fun getBoolean(key: String): Boolean?

    /**
     * Check if a key exists in storage
     */
    fun contains(key: String): Boolean

    /**
     * Remove a value from storage
     */
    fun remove(key: String)

    /**
     * Clear all values from storage
     */
    fun clear()



}