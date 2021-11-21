package ru.sfedu.cities2.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("Population")
    var population: Int? = null,
    @SerializedName("Country")
    var country: String? = null,
    @SerializedName("Name")
    var name: String? = null,
    var language: String? = null
)