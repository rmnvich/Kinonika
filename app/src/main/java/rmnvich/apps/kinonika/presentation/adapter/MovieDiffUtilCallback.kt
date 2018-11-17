package rmnvich.apps.kinonika.presentation.adapter

import android.support.v7.util.DiffUtil
import rmnvich.apps.kinonika.data.entity.Movie

class MovieDiffUtilCallback(private val oldList: List<Movie>,
                            private val newList: List<Movie>) :
        DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie.name == newMovie.name &&
                oldMovie.country == newMovie.country &&
                oldMovie.year == newMovie.year &&
                oldMovie.genre == newMovie.genre &&
                oldMovie.plot == newMovie.plot &&
                oldMovie.comment == newMovie.comment &&
                oldMovie.rating == newMovie.rating
    }
}