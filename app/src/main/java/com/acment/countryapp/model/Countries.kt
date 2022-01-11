package com.acment.countryapp.model

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("name") val name:String?,
    @SerializedName("capital")  val capital:String?,
    @SerializedName("flagPNG") val flag:String?
    )