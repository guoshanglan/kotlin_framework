package com.example.commonmodule.preference

import android.content.Context
import com.tencent.mmkv.MMKV


/**
 * 数据存储类
 * @author gukaihong
 * @date 2020/4/22
 */
class PreferencesImp private constructor() : BasePreference() {
    lateinit var kv: MMKV;

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = PreferencesImp()
    }

    override fun init(context: Context) {
        MMKV.initialize(context);
        kv = MMKV.defaultMMKV();
    }

    override fun setString(key: String, value: String?) {
        kv.encode(key, value ?: "")
    }

    override fun getString(key: String): String {
        return kv.decodeString(key)
    }

    fun getString(key: String, default: String): String {
        return kv.decodeString(key, default)?:""
    }

    override fun setBoolean(key: String, value: Boolean) {
        kv.encode(key, value)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return kv.decodeBool(key, default)
    }

    override fun setFloat(key: String, value: Float) {
        kv.encode(key, value)
    }

    override fun getFloat(key: String, default: Float): Float {
        return kv.decodeFloat(key, default)
    }

    override fun setDouble(key: String, value: Double) {
        kv.encode(key, value)
    }

    override fun getDouble(key: String, default: Double): Double {
        return kv.decodeDouble(key, default)
    }

    override fun setInt(key: String, value: Int) {
        kv.encode(key, value)
    }

    override fun getInt(key: String, default: Int): Int {
        return kv.decodeInt(key, default)
    }

    override fun removeString(key: String) {
        kv.removeValueForKey(key)
    }

    override fun removeInt(key: String) {
        kv.removeValueForKey(key)
    }

    override fun remove(key: String) {
        kv.removeValueForKey(key)
    }

    override fun setLong(key: String, value: Long) {
        kv.encode(key, value)
    }

    override fun getLong(key: String, default: Long): Long {
        return kv.decodeLong(key, default)
    }

}