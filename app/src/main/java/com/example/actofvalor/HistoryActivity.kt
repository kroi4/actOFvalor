package com.example.actofvalor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class HistoryActivity : AppCompatActivity() {

    private lateinit var count: TextView
    private lateinit var date: TextView
    private lateinit var time: TextView
    private lateinit var price: TextView


    companion object{
        const val COUNT = "COUNT"
        const val DATE = "DATE"
        const val TIME = "TIME"
        const val PRICE = "PRICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        count = findViewById(R.id.historyCountNum)
        date = findViewById(R.id.historyDateNum)
        time = findViewById(R.id.historyTimeNum)
        price = findViewById(R.id.historyPriceNum)

        val nameCount = intent.getStringExtra(COUNT)
        val dateCount = intent.getStringExtra(DATE)
        val timeCount = intent.getStringExtra(TIME)
        val priceCount = intent.getStringExtra(PRICE)

        count.setText("$nameCount")
        date.setText("$dateCount")
        time.setText("$timeCount")
        price.setText("$priceCount₪")

    }
}