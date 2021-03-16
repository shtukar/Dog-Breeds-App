package com.gmail.shtukarrv.dogbreedsapp.data

import com.gmail.shtukarrv.dogbreedsapp.R

sealed class AppException(val errorMessage: Int = R.string.blank) : Throwable()

object FetchDataException : AppException(R.string.error_fetch_data_exception)
data class ServerErrorException(val httpCode: Int? = null, val errorResponse: String?) :
    AppException()
