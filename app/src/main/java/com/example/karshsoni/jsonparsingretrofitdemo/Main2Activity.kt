package com.example.karshsoni.jsonparsingretrofitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main2Activity : AppCompatActivity() {

    lateinit var userName:String
    lateinit var userDescription:String
    companion object {
        var i = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button3.setOnClickListener {
            i++
            userName = editText2.text.toString()
            userDescription = editText3.text.toString()
            sendNetworkRequest(User(i, userName, userDescription))

        }
    }

    fun sendNetworkRequest(user: User) {
        var builder : Retrofit.Builder = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())

        var retrofit = builder.build()

        var client: UserClient = retrofit.create(UserClient::class.java)

        var call: Call<User> = client.createAccount(user)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(applicationContext, "error:( ", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                var userResp= response!!.body().toString()
                Toast.makeText(applicationContext, "Response$userResp", Toast.LENGTH_LONG).show()
            }
        })
    }
}
