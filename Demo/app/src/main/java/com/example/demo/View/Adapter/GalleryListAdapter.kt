package com.example.demo.View.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.Model.ModelMedia
import com.example.demo.R
import kotlinx.android.synthetic.main.adapter_gallery_item.view.*
import java.util.ArrayList
import kotlin.math.roundToInt

class GalleryListAdapter(
    private val context: Context,
    private var images: ArrayList<ModelMedia>
) :
    RecyclerView.Adapter<GalleryListAdapter.ColorViewHolder>() {

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        var view : ColorViewHolder? =null

        view = ColorViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_gallery_item, parent, false))
        return  view
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        Glide.with(context)
            .load("https://picsum.photos/300/300?image="+ images.get(position).id)
            .thumbnail(0.2f)
            .into(holder.iv)

        holder.name.text=images.get(position).author
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.imageViewPhoto as ImageView
        val name = view.tv_name as TextView

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}