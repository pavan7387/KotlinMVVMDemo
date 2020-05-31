package com.example.demo.DataModel.Api

import com.example.demo.Interface.ApiInterface
import com.example.demo.Model.ModelMedia
import com.lifeprint.digitalframe.Login.Interface.ApiCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ApiModel : ApiInterface {

    override fun getMedia(callBack: ApiCallBack<ArrayList<ModelMedia>>) {

        val apiClient = RetrofitInstance.client.create(RetrofitService::class.java)
        val call = apiClient?.getImages()
        call?.enqueue(object : Callback<ArrayList<ModelMedia>> {
            override fun onFailure(call: Call<ArrayList<ModelMedia>>?, t: Throwable?) {
                callBack.onError(t.toString())
            }
            override fun onResponse(call: Call<ArrayList<ModelMedia>>?, response: Response<ArrayList<ModelMedia>>) {
                if (response.isSuccessful) {
                    callBack.onSuccess(response.body()!!)
                }else{
                    callBack.onError(response.message())
                }
            }
        })
    }


}