package com.acment.countryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.acment.countryapp.R
import com.acment.countryapp.databinding.ActivityMainBinding
import com.acment.countryapp.recyclerviewAdaptor.CountryListAdaptor
import com.acment.countryapp.recyclerviewAdaptor.itemdecorator
import com.acment.countryapp.viewModel.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    lateinit var  viewModel:ListViewModel
    val countryListAdaptor = CountryListAdaptor(this, arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        mainBinding.countryrecyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter= countryListAdaptor
            addItemDecoration(itemdecorator(resources.getDimension(R.dimen._10sdp).toInt()))
        }
        observeModel()

    }
    fun observeModel(){

        viewModel.countries.observe(this, Observer {
            countries-> countries.let {
            mainBinding.countryrecyclerList.visibility = View.VISIBLE
                countryListAdaptor.updateCountries(it)
        }
        })

        viewModel.countryLoadError.observe(this, Observer {
            error-> error.let { mainBinding.errorText.visibility = if (it) View.VISIBLE else View.GONE}
        })

        viewModel.Loading.observe(this, Observer {
            loading-> loading.let { mainBinding.progressBar.visibility =
                            if (it) View.VISIBLE else View.GONE
            if (it){
                mainBinding.errorText.visibility= View.GONE
                mainBinding.countryrecyclerList.visibility = View.GONE
            }}
        })


    }
}