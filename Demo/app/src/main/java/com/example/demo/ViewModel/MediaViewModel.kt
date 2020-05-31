package com.example.demo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.Interface.ApiInterface
import com.example.demo.Model.ModelMedia
import com.lifeprint.digitalframe.Login.Interface.ApiCallBack


class MediaViewModel(private val mMediaViewModel : ApiInterface) : ViewModel() {

    private val mImageSuccess = MutableLiveData<ArrayList<ModelMedia>>()
    private val mImageFaild = MutableLiveData<String>()
    private var mList = ArrayList<ModelMedia>()

    fun getImages():LiveData<ArrayList<ModelMedia>>{
        return mImageSuccess
    }

    fun getImageFaild():LiveData<String>{
        return mImageFaild
    }

    fun getMedia(context: Context) {
      mMediaViewModel.getMedia(object :ApiCallBack<ArrayList<ModelMedia>>{
          override fun onError(error: String?) {
             mImageFaild.postValue(error.toString())
          }

          override fun onSuccess(data: ArrayList<ModelMedia>) {
              mList = data
             mImageSuccess.postValue(data)
          }
      })
    }

    fun getMediaList(): ArrayList<ModelMedia> {
          return mList
    }

}