package com.student.drop.util

import com.tencent.mmkv.MMKV

object MySPUtils {
    private var kv: MMKV = MMKV.defaultMMKV()
    fun encodeBool(k: String, v: Boolean) {
        kv.encode(k, v)
    }

    fun decodeBool(k: String): Boolean {
        return kv.decodeBool(k, false)
    }

    fun encodeString(k: String, v: String = "") {
        kv.encode(k, v)
    }

    fun decodeString(k: String): String {
        return kv.decodeString(k, "")
    }

    fun encodeInt(k: String, v: Int) {
        kv.encode(k, v)
    }

    fun decodeInt(k: String): Int {
        return kv.decodeInt(k, 0)
    }

    fun encodeLong(k: String, v: Long) {
        kv.encode(k, v)
    }

    fun decodeLong(k: String): Long {
        return kv.decodeLong(k, 0L)
    }
}