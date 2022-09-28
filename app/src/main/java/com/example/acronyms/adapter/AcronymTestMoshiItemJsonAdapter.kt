// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress(
    "DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION"
)

package com.example.acronyms.adapter

import com.example.acronyms.data.model.AcronymTestMoshiItem
import com.example.acronyms.data.model.LfX
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.emptySet
import kotlin.text.buildString

public class AcronymTestMoshiItemJsonAdapter(
    moshi: Moshi
) : JsonAdapter<AcronymTestMoshiItem>() {
    private val options: JsonReader.Options = JsonReader.Options.of("lfs", "sf")

    private val listOfLfXAdapter: JsonAdapter<List<LfX>> =
        moshi.adapter(
            Types.newParameterizedType(List::class.java, LfX::class.java),
            emptySet(),
            "lfs"
        )

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(
        String::class.java,
        emptySet(),
        "sf"
    )

    public override fun toString(): String = buildString(42) {
        append("GeneratedJsonAdapter(").append("AcronymTestMoshiItem").append(')')
    }

    public override fun fromJson(reader: JsonReader): AcronymTestMoshiItem {
        var lfs: List<LfX>? = null
        var sf: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> lfs = listOfLfXAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "lfs",
                    "lfs",
                    reader
                )
                1 -> sf = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("sf", "sf", reader)
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return AcronymTestMoshiItem(
            lfs = lfs ?: throw Util.missingProperty("lfs", "lfs", reader),
            sf = sf ?: throw Util.missingProperty("sf", "sf", reader)
        )
    }

    public override fun toJson(writer: JsonWriter, value_: AcronymTestMoshiItem?) {
        if (value_ == null) {
            throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("lfs")
        listOfLfXAdapter.toJson(writer, value_.lfs)
        writer.name("sf")
        stringAdapter.toJson(writer, value_.sf)
        writer.endObject()
    }
}