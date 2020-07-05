package com.example.taller03.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taller03.Model.UsersModel
import com.example.taller03.R
import com.example.taller03.UserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class UsersAdapter(private var arrayList:ArrayList<UsersModel>, private val listener: UsersModelHolder.OnAdapterListener):
    RecyclerView.Adapter<UsersAdapter.UsersModelHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersModelHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return UsersModelHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    override fun onBindViewHolder(holder: UsersModelHolder, position: Int) {
        val post: UsersModel = this.arrayList[position]
        holder.itemView.text_userName.text = post.username
        holder.itemView.text_body.text = post.body
        holder.itemView.text_likes.text = post.likes.toString()

        if (!post.user_image.isBlank()) {
            Picasso.get()
                .load(post.user_image)
                .into(holder.itemView.profile_image)
        }
        if (!post.image.isBlank()) {
            Picasso.get()
                .load(post.image)
                .into(holder.itemView.img_body)
        }

        holder.itemView.setOnClickListener { listener.onItemClickListener(post) }
    }

    class UsersModelHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

        interface OnAdapterListener {
            fun onItemClickListener( item: UsersModel)
        }

    }

    fun updateList(postList: List<UsersModel>) {
        this.arrayList = postList as ArrayList<UsersModel>
        this.notifyDataSetChanged()
    }

}