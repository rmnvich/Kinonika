package rmnvich.apps.kinonika.presentation.activity.review.mvp

import android.graphics.Bitmap
import android.os.Environment
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.Callable

class ReviewActivityModel(
        private val databaseRepository: DatabaseRepository) :
        ReviewActivityContract.Model {

    override fun getMovieById(movieId: Long): Single<Movie> {
        return databaseRepository.getMovieById(movieId)
    }

    override fun getPhotoUri(bitmap: Bitmap): Observable<File> {
        return Observable.fromCallable(CallableBitmapAction(bitmap))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    inner class CallableBitmapAction(private var bitmap: Bitmap) : Callable<File> {

        override fun call(): File {
            val calendar = Calendar.getInstance()

            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val file = File("${Environment.getExternalStorageDirectory()}${File.separator}" +
                    "temp_file${calendar.timeInMillis}.jpg")
            try {
                file.createNewFile()
                val fo = FileOutputStream(file)
                fo.write(bytes.toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return file
        }
    }
}