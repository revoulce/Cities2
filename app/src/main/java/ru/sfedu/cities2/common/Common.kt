package ru.sfedu.cities2.common

import ru.sfedu.cities2.`interface`.RetrofitServices
import ru.sfedu.cities2.retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "https://raw.githubusercontent.com/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}