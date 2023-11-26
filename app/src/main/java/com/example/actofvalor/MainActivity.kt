package com.example.actofvalor

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var aovAnimate: ImageView
    private lateinit var buyTicket: Button
    private lateinit var pictures: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        aovAnimate = findViewById(R.id.imgAOV)
        pictures = findViewById(R.id.btnCommandoPicture)
        buyTicket = findViewById(R.id.btnBuyTicket)

        aovAnimate.setOnClickListener(){
            aovAnimate.animate().apply {
                duration = 800
                rotationXBy(360f)
            }.start()
        }

        buyTicket.setOnClickListener() {
            Log.i("MYTAG","NextPage clicked - order")
            val nextPageOrder = Intent(this,SecondActivity::class.java)
            startActivity(nextPageOrder)
        }

        pictures.setOnClickListener() {
            Log.i("MYTAG","NextPage clicked - Pictures")
            val nextPagePicture = Intent(this,PicturesActivity::class.java)
            startActivity(nextPagePicture)
        }


    }
}