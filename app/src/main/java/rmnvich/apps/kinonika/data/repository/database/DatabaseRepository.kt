package rmnvich.apps.kinonika.data.repository.database

import rmnvich.apps.kinonika.data.repository.database.utils.AppDatabase

class DatabaseRepository(appDatabase: AppDatabase) {

    private val movieDao = appDatabase.movieDao()
    private val tagDao = appDatabase.tagDao()


}