package com.example.myapplication44.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication44.R
import com.example.myapplication44.pojo.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter. photoViewHolder>() {
    var listModel: List<Photo> = emptyList()

    fun setList(listModel: List<Photo>) {
        this.listModel = listModel
        notifyDataSetChanged()
    }

    inner class  photoViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.photoText)
        var image: ImageView = itemView.findViewById(R.id.ivPhoto)


        fun bind(Model: Photo) {
            title.text = Model.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  photoViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return  photoViewHolder(view)
    }

    override fun onBindViewHolder(holder:  photoViewHolder, position: Int) {
        var Model: Photo = listModel.get(position)
        holder.bind(Model)
        Picasso.get()
            .load(Model.url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }


}
