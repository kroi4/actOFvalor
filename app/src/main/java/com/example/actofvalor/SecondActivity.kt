package com.example.actofvalor

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Month
import java.time.Year
import java.util.*

class SecondActivity : AppCompatActivity() {

    private lateinit var orderButton:Button
    private lateinit var count:EditText
    private lateinit var date:EditText
    private lateinit var time:EditText
    private lateinit var sumView:TextView
    private lateinit var HistoryPage:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()


        sumView = findViewById(R.id.sumPrice)
        orderButton = findViewById(R.id.buttonOrderNow)
        HistoryPage = findViewById(R.id.buttonHistory)
        count = findViewById(R.id.editTextCount)
        date = findViewById(R.id.editTextDate)
        time = findViewById(R.id.editTextTime)

        val price = 32

        count.addTextChangedListener() {
            var len = count.text.toString()
            if (len.length == 1 && !(len.contains('-'))){
                var sum = count.text.toString().toInt()
                if (sum == 0){
                    count.setText("1")
                    count.setError("The min is 1")
                }
                else{
                    sum *= price
                    sumView.setText("$sum₪")
                    Log.i("MYTAG", "Count clicked, count = $sum")
                }
                sum = 0
            }

            else if (len.contains("0")) {
                sumView.setText("0₪")
            }

            else if (len.contains("-")) {
                count.setError("Invalid number")
                count.setText("")
            }

            else if (len.length > 1) {
                count.setError("The max is 9")
                var sum = count.text.toString().toInt()
                sum /= 10
                count.setText("$sum")
            }
        }


        var c = Calendar.getInstance()

        val minDay = c[Calendar.DAY_OF_MONTH]+1
        val minMonth = c[Calendar.MONTH]
        val minYear = c[Calendar.YEAR]

        val maxDay = c[Calendar.DAY_OF_MONTH]+30
        val maxMonth = c[Calendar.MONTH]
        val maxYear = c[Calendar.YEAR]

        date.setOnClickListener(){

            val mDialog = DatePickerDialog(this, { _, mYear, mMonth, mDay ->

                date.setText("${mDay}/${mMonth+1}/$mYear")

                c[Calendar.YEAR] = mYear

                c[Calendar.MONTH] = mMonth

                c[Calendar.DAY_OF_MONTH] = mDay

            }, c[Calendar.YEAR], c[Calendar.MONTH], c[Calendar.DAY_OF_MONTH])


            c.set(minYear, minMonth, minDay)

            mDialog.datePicker.minDate = c.timeInMillis

            c.set(maxYear, maxMonth, maxDay)

            mDialog.datePicker.maxDate = c.timeInMillis

            mDialog.show()


            Log.i("MYTAG","Date clicked")
        }

        time.setOnClickListener(){
            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{view,hourOfDay, minute -> time.setText("$hourOfDay:$minute")},c.get(Calendar.HOUR),c.get(Calendar.MINUTE),true).show()

            Log.i("MYTAG","Time clicked, Hour: ${time.text.toString()}}")
        }

        var countNum = Any()
        var dateNum = Any()
        var timeNum = Any()
        var sumNum = Any()

        orderButton.setOnClickListener() {
            HistoryPage.isEnabled = true
            Toast.makeText(applicationContext, "Order succeeded!", Toast.LENGTH_SHORT).show()
            countNum = count.text.toString()
            dateNum = date.text.toString()
            timeNum = time.text.toString()
            if ((countNum as String).length == 1 && !((countNum as String).contains('-'))) {
                sumNum = (countNum as String).toInt() * price
            }
        }

        HistoryPage.setOnClickListener() {
            Log.i("MYTAG", "NextPage clicked - History")
            val intent = Intent(this@SecondActivity, HistoryActivity::class.java)

            intent.putExtra(HistoryActivity.PRICE, sumNum.toString())

            intent.putExtra(HistoryActivity.COUNT, countNum as String)
            intent.putExtra(HistoryActivity.DATE, dateNum as String)
            intent.putExtra(HistoryActivity.TIME, timeNum as String)
            intent.putExtra(HistoryActivity.PRICE, sumNum.toString())

            startActivity(intent)
        }

        count.addTextChangedListener(loginTextWatcher)
        date.addTextChangedListener(loginTextWatcher)
        time.addTextChangedListener(loginTextWatcher)

    }

    private val loginTextWatcher = object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val textCount = count.text.toString().trim()
            val textDate = date.text.toString().trim()
            val textTime = time.text.toString().trim()

            orderButton.isEnabled = textCount.isNotEmpty() && textDate.isNotEmpty() && textTime.isNotEmpty()

        }

        override fun afterTextChanged(p0: Editable?) {}
    }
}