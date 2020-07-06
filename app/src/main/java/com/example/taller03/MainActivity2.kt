package com.example.taller03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Estado", "onCreate")


        fab.setOnClickListener { view ->

            // Intent intent = new Intent(this, SecondActivity.class); <- Java
            val intent = Intent(this, MainActivity::class.java)
            // intent.putExtra("usuario", "Everis")
            startActivity(intent)
        }
        btn_amigos.setOnClickListener { view ->

            // Intent intent = new Intent(this, SecondActivity.class); <- Java
            val intent = Intent(this, AmigosActivity::class.java)
            // intent.putExtra("usuario", "Everis")
            startActivity(intent)
        }

        callService()
    }

    private fun callService() {
        val service = Repository.RetrofitRepository.getService()

        //GlobalScope.launch(Dispatchers.IO)
        //CoroutineScope(Dispatchers.IO).launch
        GlobalScope.launch(Dispatchers.IO) {
            val response =  service.getProfile()

            withContext(Dispatchers.Main) {
                /**
                 * Actualizar la interfaz grafica
                 */
                try {
                    if(response.isSuccessful) {

                        val user : UserResponse?  = response.body()
                        if( user != null) updateInfo(user)
                    }else{
                        Toast.makeText(this@MainActivity2, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e : HttpException) {
                    Toast.makeText(this@MainActivity2, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateInfo(user: UserResponse) {
        if(user.image.isNotEmpty()){
            Picasso.get().load(user.image).into(profile_image)
        }

        profile_fullname.text = String.format("%s %s", user.name, user.lastname)
        profile_email.text = user.email
        profile_years.text = user.age
        profile_location.text = user.location
        profile_occupation.text = user.occupation
        profile_likes.text = " LIKES: \n"+ user.social.likes.toString()
        profile_posts.text = " POST:  \n"+ user.social.posts.toString()
        profile_shares.text = " SHARES: \n"+ user.social.shares.toString()
        profile_friends.text = " FRIENDS: \n"+ user.social.shares.toString()
    }

}