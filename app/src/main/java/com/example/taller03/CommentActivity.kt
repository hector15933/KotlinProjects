package com.example.taller03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller03.Adapter.ComentAdapter
import com.example.taller03.Model.ComentModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_comment.*
import org.json.JSONArray


class CommentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)


        var intent = getIntent()
        var user_name = intent.getStringExtra("ARRAY_COMMENT")


        val gson = GsonBuilder().create()
        val theList = gson.fromJson<ArrayList<ComentModel>>(user_name, object :
            TypeToken<ArrayList<ComentModel>>(){}.type)

        Toast.makeText(this,"Hola prros tu string : ${theList.size}",Toast.LENGTH_LONG).show()

        val arrayList= ArrayList<ComentModel>()

        for(num in 0..theList.size-1) {

           /* arrayList.add(ComentModel(2,"@david2k",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS4PIZvbpWjqXv4cGkJtBRI6Hc0PGzneT7AcsVWR7qjnpOa7ckT"
                ,"Amazing! ♥"
            ))*/
           arrayList.add(ComentModel(theList.get(num).user_id,theList.get(num).username,theList.get(num).user_image,theList.get(num).comment))

        }


        val ComentAdapter = ComentAdapter(arrayList,this)
        reciclerView_Comment.layoutManager= LinearLayoutManager(this)
        reciclerView_Comment.adapter=ComentAdapter
        /*
        val arrayList= ArrayList<ComentModel>()

        val comentAdapter = ComentAdapter(arrayList,this)

        reciclerView_Main.layoutManager= LinearLayoutManager(this)
        reciclerView_Main.adapter=comentAdapter*/
    }


}