package rmnvich.apps.kinonika.data.repository.database.utils

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import rmnvich.apps.kinonika.data.entity.Genre
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.entity.Tag
import rmnvich.apps.kinonika.data.repository.database.utils.dao.GenreDao
import rmnvich.apps.kinonika.data.repository.database.utils.dao.MovieDao
import rmnvich.apps.kinonika.data.repository.database.utils.dao.TagDao

@Database(entities = [Movie::class, Tag::class, Genre::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    abstract fun tagDao() : TagDao

    abstract fun genreDao() : GenreDao
}