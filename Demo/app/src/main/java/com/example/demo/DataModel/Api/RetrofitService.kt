package com.example.demo.DataModel.Api

import com.example.demo.Model.ModelMedia
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService{

    @GET("/list")
    fun getImages() :Call<ArrayList<ModelMedia>>
}