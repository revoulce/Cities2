package ru.sfedu.cities2.`interface`

import retrofit2.Call
import retrofit2.http.GET
import ru.sfedu.cities2.model.City

interface RetrofitServieces {
    @GET("city")
    fun getCityList(): Call<MutableList<City>>
}