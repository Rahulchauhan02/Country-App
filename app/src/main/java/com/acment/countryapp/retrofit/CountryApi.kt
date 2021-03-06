package com.acment.countryapp.retrofit

import com.acment.countryapp.model.Countries
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {

  @GET("DevTides/countries/master/countriesV2.json")
  fun getCountries():Single<List<Countries>>

}