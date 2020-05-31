package com.example.demo.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo.Interface.ApiInterface

class MediaViewModelFactory(private val repository:ApiInterface):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MediaViewModel(repository) as T
    }
}