package com.example.demo.Interface

import androidx.lifecycle.ViewModelProvider
import com.example.demo.DataModel.Api.ApiModel
import com.example.demo.ViewModel.MediaViewModelFactory

object MediaInjection {
    private val mMediaDataSource : ApiInterface = ApiModel()
    private val mMediaViewModelFactory = MediaViewModelFactory(mMediaDataSource)

    fun provideViewModelFactory() : ViewModelProvider.Factory{
        return mMediaViewModelFactory
    }
}