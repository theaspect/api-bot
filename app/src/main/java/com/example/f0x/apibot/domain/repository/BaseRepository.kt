package com.example.f0x.apibot.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by f0x on 28.11.17.
 */
open class BaseRepository {
    protected val tag: String = this.javaClass.simpleName

    fun <T> createErrorObservable(throwable: Throwable): Observable<T> {
        return Observable.error(parseException(throwable))
    }

    fun createErrorCompletable(throwable: Throwable): Completable {
        return Completable.error(parseException(throwable))
    }

    fun <T> createErrorSingle(throwable: Throwable): Single<T> {
        return Single.error(parseException(throwable))
    }


    internal fun parseException(t: Throwable): Throwable {
        t.printStackTrace()
//        if (t is HttpException) {
//            return try {
//                val e = AppController.provideGson().fromJson(t.response().errorBody()!!.string(), BackendError::class.java)
//                RepositoryException(e)
//            } catch (e1: IOException) {
//                e1.printStackTrace()
//                RepositoryException(t)
//            }
//
//        }
        return RepositoryException(t)
    }

}