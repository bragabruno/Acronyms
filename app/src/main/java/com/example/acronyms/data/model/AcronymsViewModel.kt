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

    val listAcronyms = MutableLiveData<List<AcronymList>>()

    fun setSearchAcronyms(sf: String) {
        RetrofitClient.apiInstance
            .getAcronyms(sf)
            .enqueue(object : Callback<List<AcronymList>> {
                override fun onResponse(
                    call: Call<List<AcronymList>>,
                    response: Response<List<AcronymList>>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("Unsuccessful", "Unsuccessful Acronyms Search Response!")
                    }
                    listAcronyms.postValue(response.body()!!)
                    Log.d("Success", "Successful Acronyms Search Response!")
                }
                override fun onFailure(call: Call<List<AcronymList>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getSearchAcronyms(): LiveData<List<AcronymList>> {
        return listAcronyms
    }
}
