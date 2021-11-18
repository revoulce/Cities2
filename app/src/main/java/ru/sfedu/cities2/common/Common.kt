package ru.sfedu.cities2.common

import ru.sfedu.cities2.`interface`.RetrofitServieces
import ru.sfedu.cities2.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/City.json"

    val retrofitService: RetrofitServieces get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}