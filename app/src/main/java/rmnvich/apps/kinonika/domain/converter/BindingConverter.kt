package rmnvich.apps.kinonika.domain.converter

import android.databinding.BindingConversion
import rmnvich.apps.kinonika.data.entity.Genre
import rmnvich.apps.kinonika.data.entity.Tag


class BindingConverter {

    @BindingConversion
    fun convertGenresToString(genres: List<Genre>): String {
        val sb = StringBuilder()
        for (genre in genres) {
            if (sb.isNotEmpty()) sb.append(", ")
            sb.append(genre.name)
        }
        return sb.toString()
    }

    @BindingConversion
    fun convertTagsToString(tags: List<Tag>): String {
        val sb = StringBuilder()
        for (tag in tags) {
            if (sb.isNotEmpty())
                sb.append(" ")
            sb.append("#" + tag.hashTag)
        }
        return sb.toString()
    }

}