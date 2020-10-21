package com.arkountos.orcsattack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MagicItemGenerator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magic_item_generator)

        Log.d("Magic", ":O")
        var textview = findViewById<TextView>(R.id.response)

//        var result = ""

        val queue = Volley.newRequestQueue(this)

        val randomMagicItemNumber = (0..238).random()
        val randomMagicItemPage = randomMagicItemNumber / 50
        val randomMagicItemIndex = randomMagicItemNumber % 50

        var url = "https://api.open5e.com/magicitems/"
        when (randomMagicItemPage){
            1 -> url += ""
            2 -> url += "?page=2"
            3 -> url += "?page=3"
            4 -> url += "?page=4"
            5 -> url += "?page=5"
        }
//        Log.d("Item nums", "$randomMagicItemNumber, $randomMagicItemPage, $randomMagicItemIndex")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
            textview.text = response.getJSONArray("results").get(randomMagicItemIndex).toString()
        },
            {
                textview.text = "Nope!"
            })

        queue.add(jsonObjectRequest)
    }
}