package com.example.commonmodule.preference

import android.content.Context

/**
 * 数据存储类(基类)
 * @author gukaihong
 * @date 2020/4/22
 */
abstract class BasePreference {
    /**初始化*/
    abstract fun init(context: Context)
    abstract fun setString(key: String, value: String?)
    abstract fun getString(key: String): String?
    abstract fun setBoolean(key: String, value: Boolean)
    abstract fun getBoolean(key: String, default: Boolean = false): Boolean?
    abstract fun setFloat(key: String, value: Float)
    abstract fun getFloat(key: String, default: Float = 0f): Float?
    abstract fun setDouble(key: String, value: Double)
    abstract fun getDouble(key: String, default: Double = 0.0): Double?
    abstract fun setInt(key: String, value: Int)
    abstract fun getInt(key: String, default: Int = 0): Int?
    abstract fun setLong(key: String, value: Long)
    abstract fun getLong(key: String, default: Long = 0L): Long?
    abstract fun removeString(key: String)
    abstract fun removeInt(key: String)
    abstract fun remove(key: String)
}