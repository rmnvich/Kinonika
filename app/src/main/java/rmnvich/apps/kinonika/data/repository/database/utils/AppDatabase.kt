package rmnvich.apps.kinonika.data.repository.database.utils

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.utils.dao.MovieDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}