package com.example.demo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo.Interface.MediaInjection
import com.example.demo.Model.ModelMedia
import com.example.demo.Util.AppUtil
import com.example.demo.View.Adapter.GalleryListAdapter
import com.example.demo.ViewModel.MediaViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mGalleryListAdapter: GalleryListAdapter
    private lateinit var photoGridManager: GridLayoutManager
    private lateinit var mMediaViewModel: MediaViewModel
    private  var mDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        getMediaList()
    }

    private fun setUpViewModel() {
        mMediaViewModel = ViewModelProvider(this,MediaInjection.provideViewModelFactory()).get(MediaViewModel::class.java)
        setObserver()
    }

    private fun setObserver() {
        mMediaViewModel.getImages().observe(this, Observer { it ->
            setPhotoAdapter(it)
            mDialog?.dismiss()
        })
        mMediaViewModel.getImageFaild().observe(this,Observer{
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            mDialog?.dismiss()
        })
    }

    private fun getMediaList() {
        if (mMediaViewModel.getMediaList().size == 0) {
            mDialog = AppUtil.showProgressDialog(this)
            mMediaViewModel.getMedia(this)
        }
    }

    private fun setPhotoAdapter(it: ArrayList<ModelMedia>) {
        photoGridManager = GridLayoutManager(this, 2)
        rv_image.layoutManager = photoGridManager
        mGalleryListAdapter = GalleryListAdapter(this,it)
        rv_image.adapter = mGalleryListAdapter
        rv_image.itemAnimator = null
    }
}
