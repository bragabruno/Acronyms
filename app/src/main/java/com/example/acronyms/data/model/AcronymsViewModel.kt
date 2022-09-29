package com.example.acronyms.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronyms.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcronymsViewModel : ViewModel() {

    val listAcronyms = MutableLiveData<AcronymTestMoshiItem>()

    fun setSearchAcronyms(sf: String) {
        RetrofitClient.apiInstance
            .getAcronyms(sf)
            .enqueue(object : Callback<AcronymTestMoshiItem> {
                override fun onResponse(
                    call: Call<AcronymTestMoshiItem>,
                    response: Response<AcronymTestMoshiItem>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("Unsuccessful", "Unsuccessful Acronyms Search Response!")
                    }
                    listAcronyms.postValue(response.body()!!)
                    Log.d("Success", "Successful Acronyms Search Response!")
                }
                override fun onFailure(call: Call<AcronymTestMoshiItem>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getSearchAcronyms(): LiveData<AcronymTestMoshiItem> {
        return listAcronyms
    }
}
