package com.acment.countryapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acment.countryapp.model.Countries
import com.acment.countryapp.retrofit.RetrofitService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableSingleObserver

import io.reactivex.schedulers.Schedulers


class ListViewModel():ViewModel() {

   var retrofitService = RetrofitService()
    val disposable = CompositeDisposable()
    var countries = MutableLiveData<List<Countries>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val Loading = MutableLiveData<Boolean>()


    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        Loading.value= true
        disposable.add(
            retrofitService.getCountryList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Countries>>(){
                    override fun onSuccess(value: List<Countries>?) {
                        countries.value = value
                        Log.e("data",value.toString())
                        Loading.value= false
                        countryLoadError.value= false
                    }

                    override fun onError(e: Throwable?) {
                       countryLoadError.value= true
                        Loading.value= false
                        Log.e("error",e.toString())

                    }
                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}