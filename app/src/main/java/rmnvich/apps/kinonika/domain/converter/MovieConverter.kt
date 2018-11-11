package rmnvich.apps.kinonika.domain.converter

import android.arch.persistence.room.TypeConverter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import rmnvich.apps.kinonika.data.entity.Genre
import rmnvich.apps.kinonika.data.entity.Tag
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class MovieConverter {

    private val gson = Gson()

    @TypeConverter
    fun toListOfTags(data: String?): List<Tag> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromListOfTags(tags: List<Tag>): String {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun toListOfGenre(data: String?): List<Genre> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromListOfGenre(genres: List<Genre>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeStream(ByteArrayInputStream(data))
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        return if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.toByteArray()
        } else ByteArray(0)
    }
}