package com.example.taller03

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller03.Adapter.UsersAdapter
import com.example.taller03.Model.ComentModel
import com.example.taller03.Model.UsersModel
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,UsersAdapter.UsersModelHolder.OnAdapterListener{

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("Estado", "onCreate")
        callService()

        adapter = UsersAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        reciclerView_Main.layoutManager= linearLayoutManager
        reciclerView_Main.adapter = adapter

        callService()

    }


    private fun callService() {
        val service = Repository.RetrofitRepository.getService()

        GlobalScope.launch(Dispatchers.IO) {
            val response =  service.getPosts()

            withContext(Dispatchers.Main) {

                try {
                    if(response.isSuccessful) {
                        val posts : List<UsersModel>? = response.body()
                        val childs:List<ComentModel>

                        if( posts != null) updateInfo(posts)
                    }else{
                        Toast.makeText(this@MainActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e : HttpException) {
                    Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Estado", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Estado", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Estado", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Estado", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Estado", "onDestroy")
    }

    override fun onItemClickListener(item: UsersModel) {

    }

    private fun updateInfo(list: List<UsersModel>) {
        adapter.updateList(list)
    }

}



