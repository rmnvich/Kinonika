package rmnvich.apps.kinonika.data.repository.database.utils.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Tag

@Dao
interface TagDao {

    @Query("SELECT * FROM tag")
    fun getAllTags(): Flowable<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTags(tags: List<Tag>)
}