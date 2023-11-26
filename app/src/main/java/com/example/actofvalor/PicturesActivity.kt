package com.example.actofvalor

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView



class PicturesActivity : AppCompatActivity() {


    private lateinit var web: WebView
    private lateinit var vid: VideoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        vid = findViewById(R.id.videoView)
        web = findViewById(R.id.webView)


        val mediaController = MediaController(this)
        mediaController.setAnchorView(vid)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.vidaov}")
        vid.setMediaController(mediaController)
        vid.setVideoURI(uri)
        vid.requestFocus()
        vid.start()


        web.webViewClient = WebViewClient()
        web.loadUrl("https://youtu.be/ZnlPgo9TaGo")
        web.settings.javaScriptEnabled = true
        web.settings.setSupportZoom(true)
        web.settings.safeBrowsingEnabled = true

    }

    override fun onBackPressed() {

        if (web.canGoBack())

            web.goBack()
        else
            super.onBackPressed()
    }
}