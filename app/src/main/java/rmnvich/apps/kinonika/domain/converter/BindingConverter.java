package rmnvich.apps.kinonika.domain.converter;

import android.databinding.BindingConversion;

import java.util.List;

import rmnvich.apps.kinonika.data.entity.Genre;
import rmnvich.apps.kinonika.data.entity.Tag;

public class BindingConverter {

    @BindingConversion
    public static String convertGenresToString(List<Genre> genres) {
        StringBuilder sb = new StringBuilder();
        for (Genre genre : genres) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(genre.getName());
        }
        return sb.toString();
    }

    @BindingConversion
    public static String convertTagsToString(List<Tag> tags) {
        StringBuilder sb = new StringBuilder();
        for (Tag tag : tags) {
            if (sb.length() > 0) sb.append(" ");
            sb.append("#").append(tag.getHashTag());
        }
        return sb.toString();
    }
}
