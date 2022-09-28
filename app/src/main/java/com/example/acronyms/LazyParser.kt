package com.example.acronyms

import android.database.sqlite.SQLiteDatabase.create
import com.example.acronyms.adapter.LfXJsonAdapter
import com.example.acronyms.data.model.LfX
import com.example.acronyms.data.model.VarX
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi

class LazyParser(moshi: Moshi) {
    private val lfsAdapter: JsonAdapter<LfX> = moshi.adapter(LfX::class.java)

    fun parse(reader: JsonReader): Sequence<LfX> {
        return sequence {
            reader.readArray {
                yield(lfsAdapter.fromJson(reader)!!)
            }
        }
    }

    private val sequence =
        LazyParser(moshi)
//            .parse(
////                lfsAdapter.fromJsonValue()
////                LfX(1,"lf", 1, emptyList<VarX>()) as JsonReader
//            )
//    val filteredList = sequence.filter { it.lf >= 18.toString() }.toList()

    fun JsonReader.skipNameAndValue() {
        skipName()
        skipValue()
    }
    inline fun JsonReader.readObject(body: () -> Unit) {
        beginObject()
        while (hasNext()) {
            body()
        }
        endObject()
    }

    inline fun JsonReader.readArray(body: () -> Unit) {
        beginArray()
        while (hasNext()) {
            body()
        }
        endArray()
    }

    inline fun <T : Any> JsonReader.readArrayToList(body: () -> T?): List<T> {
        val result = mutableListOf<T>()
        beginArray()
        while (hasNext()) {
            body()?.let { result.add(it) }
        }
        endArray()
        return result
    }
}

class ManualParser {
    companion object {
        val NAMES = JsonReader.Options.of("id", "name", "age")
    }

//    fun parse(reader: JsonReader): ArrayList<LfX> {
//        return reader.readArrayToList {
//            var freq: Int = -1
//            var lf: String = ""
//            var since: Int = -1
//            var vars: List<VarX> = null
//
//            reader.readObject {
//                when (reader.selectName(NAMES)) {
//                    0 -> freq = reader.nextLong()
//                    1 -> lf = reader.nextString()
//                    2 -> since = reader.nextInt()
//                    else -> reader.skipNameAndValue()
//                }
//            }
//
//            if (freq == -1 || lf == "") {
//                throw JsonDataException("Missing required field")
//            }
//            LfX(freq, lf, since, vars)
//        }
//    }
}