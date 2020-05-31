package com.example.demo

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var isApiCalled: Boolean = false
    private lateinit var wifiReceiver: BroadcastReceiver
    private lateinit var mGalleryListAdapter: GalleryListAdapter
    private lateinit var photoGridManager: GridLayoutManager
    private lateinit var mMediaViewModel: MediaViewModel
    private  var mDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBroadcast()
        setUpViewModel()
    }

    private fun registerBroadcast() {
        wifiReceiver = WifiReceiver()
        try {
            registerReceiver(wifiReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        }catch (e: Exception){}
    }

    inner class WifiReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val conMan: ConnectivityManager? =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo: NetworkInfo? = conMan?.getActiveNetworkInfo()
            if (netInfo != null){
                if (mMediaViewModel.getMediaList().size ==0) {
                    tv_internet.visibility = View.GONE
                    rv_image.visibility = View.VISIBLE
                    getMediaList()
                }
            }else{
                 if (mMediaViewModel.getMediaList().size ==0){
                     tv_internet.visibility= View.VISIBLE
                     rv_image.visibility= View.GONE
                 }else{
                     tv_internet.visibility= View.GONE
                     rv_image.visibility= View.VISIBLE
                 }
            }
        }
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
            isApiCalled = false
        })
    }

    private fun getMediaList() {
        if (mMediaViewModel.getMediaList().size == 0 && !isApiCalled) {
            Log.d("dialogapiii","a")
            isApiCalled = true
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
