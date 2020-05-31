package com.example.demo.Interface

import android.content.Context
import com.example.demo.Model.ModelMedia
import com.lifeprint.digitalframe.Login.Interface.ApiCallBack

interface ApiInterface {
   fun getMedia(callBack: ApiCallBack<ArrayList<ModelMedia>>)
}