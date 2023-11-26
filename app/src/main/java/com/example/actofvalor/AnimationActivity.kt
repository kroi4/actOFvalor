package com.example.actofvalor

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.timerTask

class AnimationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val top = findViewById<ImageView>(R.id.topTextView)
        val mid = findViewById<ImageView>(R.id.middleTextView)
        val bot = findViewById<ImageView>(R.id.bottomTextView)

        val topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        top.startAnimation(topAnimation)
        mid.startAnimation(middleAnimation)
        bot.startAnimation(bottomAnimation)

        startMainActivity()

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun startMainActivity(){
        if(!isDestroyed){
            val intent = Intent(this,MainActivity::class.java)

            val tmtask = timerTask {
                if (!isDestroyed){
                    startActivity(intent)
                    finish()
                }
            }
            val timer = Timer()
            timer.schedule(tmtask,2200)
        }
    }


}