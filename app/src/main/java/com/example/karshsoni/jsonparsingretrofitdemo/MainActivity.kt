package com.example.karshsoni.jsonparsingretrofitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://api.github.com/"
    var userName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        var retrofit: Retrofit = builder.build()

        var client: GitHubClient = retrofit.create(GitHubClient::class.java)
        getRepo.setOnClickListener {
            userName = editText.text.toString()
            var call: Call<List<GitHubRepo>> = client.reposForUser(userName)

            call.enqueue(object : Callback<List<GitHubRepo>>{
                override fun onFailure(call: Call<List<GitHubRepo>>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "error:( ", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<List<GitHubRepo>>?, response: Response<List<GitHubRepo>>?) {
                    var repos= response!!.body()
                    val layout = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = CustomDataAdapter(repos!!, this@MainActivity)
                    recyclerView.layoutManager=layout

                }
            })
        }


    }
}
