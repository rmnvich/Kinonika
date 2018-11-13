package rmnvich.apps.kinonika.presentation.activity.make.mvp

import android.content.Intent
import android.net.Uri
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rmnvich.apps.kinonika.data.entity.Movie
import rmnvich.apps.kinonika.data.entity.Tag
import rmnvich.apps.kinonika.data.repository.database.DatabaseRepository
import rmnvich.apps.kinonika.data.repository.local.LocalRepository
import java.lang.StringBuilder
import java.util.concurrent.Callable

class MakeReviewActivityModel(private val databaseRepository: DatabaseRepository,
                              private val localRepository: LocalRepository) :
        MakeReviewActivityContract.Model {

    override fun getMovieById(movieId: Long): Single<Movie> {
        return databaseRepository.getMovieById(movieId)
    }

    override fun addMovie(movie: Movie, tags: List<String>): Completable {
        return Observable.fromCallable(CallableCollectTagsAction(movie, tags))
                .flatMapCompletable { databaseRepository.addMovieAndTags(movie, it) }
    }

    override fun getTags(): Flowable<List<String>> {
        return databaseRepository.getAllTags()
    }

    override fun getFilePath(data: Intent?): Observable<String> {
        return Observable.fromCallable(CallableBitmapAction(data?.data!!, getRealPath(data)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRealPath(data: Intent?): String {
        return localRepository.getRealPathFromUri(data?.data)
    }

    inner class CallableBitmapAction(private var uri: Uri,
                                     private var realPath: String) : Callable<String> {
        override fun call(): String {
            return localRepository.saveToInternalStorage(uri, realPath)
        }
    }

    inner class CallableCollectTagsAction(private var movie: Movie,
                                          private var existTags: List<String>) : Callable<List<Tag>> {
        override fun call(): List<Tag> {
            val listOfTags = arrayListOf<Tag>()
            val tags = movie.hashTags.split("#")

            for (tag in tags) {
                val tagName = ("#$tag").trim()

                if (!existTags.contains(tagName))
                    listOfTags.add(Tag(tagName))
            }
            listOfTags.removeAt(0)

            val stringBuilder = StringBuilder()
            for (tag in listOfTags) {
                stringBuilder.append(tag.hashTag + " ")
            }
            movie.hashTags = stringBuilder.toString().trim()

            return listOfTags
        }
    }
}