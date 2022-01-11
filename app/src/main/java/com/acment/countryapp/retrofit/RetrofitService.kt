package com.acment.countryapp.retrofit

import com.acment.countryapp.model.Countries
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    val BaseURl ="https://raw.githubusercontent.com"

    val api = Retrofit.Builder().baseUrl(BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(CountryApi::class.java)

    fun getCountryList():Single<List<Countries>>{
        return  api.getCountries()
    }


}