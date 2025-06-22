package com.example.domain.models

import android.util.Log
import java.net.ConnectException
import java.net.UnknownHostException

sealed class ExceptionsDomainModel(exception: Throwable) : Throwable(exception) {
    override val cause: Throwable = exception

    class Other(exception: Throwable) : ExceptionsDomainModel(exception)
    class NoInternet(exception: Throwable) : ExceptionsDomainModel(exception)
    class NoAuth(exception: Throwable) : ExceptionsDomainModel(exception)
}

fun Throwable.toExceptionsDomainModel(): ExceptionsDomainModel {
    Log.e("!!", this.stackTraceToString())
    return when (this) {

        is UnknownHostException, is ConnectException -> ExceptionsDomainModel.NoInternet(this)
        is retrofit2.HttpException -> {
            when (this.code()) {
                401 -> ExceptionsDomainModel.NoAuth(this)
                else -> ExceptionsDomainModel.Other(this)
            }
        }

        is ExceptionsDomainModel -> this
        else -> ExceptionsDomainModel.Other(this)
    }
}

