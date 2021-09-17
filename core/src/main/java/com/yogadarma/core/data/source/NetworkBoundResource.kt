package com.yogadarma.core.data.source

import com.yogadarma.core.data.Resource
import com.yogadarma.core.data.source.remote.network.ApiResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()

    init {
        fetchFromNetwork()
    }

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun loadData(data: RequestType): ResultType

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                mCompositeDisposable.dispose()
            }
            .subscribe { response ->
                when (response) {
                    is ApiResponse.Success -> {
//                        if (response.data != null) {
                        result.onNext(
                            Resource.Success(
                                response.message.toString(),
                                if (response.data != null) loadData(response.data) else response.data
                            )
                        )
//                        }
                    }
                    is ApiResponse.Error -> {
                        var message = "Terjadi kesalahan"

                        if (!response.message.isNullOrEmpty()) {
                            message = response.message
                        } else {
                            if (response.error is HttpException) {
                                val responseBodyString =
                                    response.error.response()?.errorBody()?.string()
                                        .toString()
                                if (isJSONValid(responseBodyString)) {
                                    val jsonObject = JSONObject(responseBodyString)
                                    try {
                                        message = jsonObject.getString("message")
                                    } catch (e: Exception) {
                                    }
                                }
                            }
                        }
                        result.onNext(Resource.Error(message))
                    }
                }
            }

        mCompositeDisposable.add(response)
    }

    fun asFlowable(): Flowable<Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)

    private fun isJSONValid(string: String): Boolean {
        try {
            JSONObject(string)
        } catch (ex: JSONException) {
            try {
                JSONArray(string)
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }
}