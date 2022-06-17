package com.example.myapplication44.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication44.R
import com.example.myapplication44.pojo.User

class UserAdapter : RecyclerView.Adapter<UserAdapter. UserViewHolder>() {
      var listModel: List<User> = emptyList()

    fun setList(listModel: List<User>) {
        this.listModel = listModel
        notifyDataSetChanged()
    }

    inner class  UserViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var username: TextView = itemView.findViewById(R.id.username)
        var email: TextView = itemView.findViewById(R.id.email)
        var address: TextView = itemView.findViewById(R.id.address)

        fun bind(Model: User) {
            name.text = Model.name
            username.text = Model.username
            email.text = Model.email
            address.text = Model.address.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  UserViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return  UserViewHolder(view)
    }

    override fun onBindViewHolder(holder:  UserViewHolder, position: Int) {
        var Model: User = listModel.get(position)
        holder.bind(Model)

    }

    override fun getItemCount(): Int {
        return listModel.size
    }


}

