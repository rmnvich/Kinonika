package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.content.Intent
import android.graphics.Bitmap
import io.reactivex.Completable
import io.reactivex.Single
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.data.repository.local.LocalRepository

class MakeReviewActivityModel(val databaseRepository: DatabaseRepository,
                              val localRepository: LocalRepository) :
        MakeReviewActivityContract.Model {

    override fun getMovieById(movieId: Long): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addMovie(movie: Movie): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBitmapFromGallery(data: Intent?): Bitmap {
        return localRepository.getBitmap(data?.data)
    }
}