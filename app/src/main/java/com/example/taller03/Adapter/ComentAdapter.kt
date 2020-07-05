package com.example.taller03.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taller03.Model.ComentModel
import com.example.taller03.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_coment.view.*

class ComentAdapter(private var arrayList:ArrayList<ComentModel>, private val listener: ComentModelHolder.OnAdapterListener):
    RecyclerView.Adapter<ComentAdapter.ComentModelHolder>()
{

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentModelHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_coment, parent, false)
        return ComentAdapter.ComentModelHolder(v)
        /*reciclerView_Coment*/
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ComentModelHolder, position: Int) {
        val post: ComentModel = this.arrayList[position]
        holder.itemView.textView_userName.text = post.username
        if (!post.user_image.isBlank()) {
            Picasso.get()
                .load(post.user_image)
                .into(holder.itemView.imageView_imgComentarista)
        }
        holder.itemView.textView_comentario.text = post.comment



        holder.itemView.setOnClickListener { listener.onItemClickListener(post) }
    }

    class ComentModelHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

        interface OnAdapterListener {
            fun onItemClickListener(item: ComentModel)
        }

    }
    fun updateList(postList: List<ComentModel>) {
        this.arrayList = postList as ArrayList<ComentModel>
        this.notifyDataSetChanged()
    }
}


