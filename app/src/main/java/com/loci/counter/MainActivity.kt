package com.loci.counter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val countBtn = findViewById<Button>(R.id.countBtn)
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        val countText = findViewById<TextView>(R.id.countText)
        val resetBtn = findViewById<TextView>(R.id.resetBtn)
        val sharedPreference = getSharedPreferences("prf", 0)

        count = sharedPreference.getInt("count", 0)
        countText.text = count.toString()

        countBtn.setOnClickListener {
            count++
            countText.text = count.toString()
        }

        minusBtn.setOnClickListener {
            count--
            countText.text = count.toString()
        }


        resetBtn.setOnClickListener {
            count = 0
            countText.text = count.toString()
        }

    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        val sharedPreference = getSharedPreferences("prf", 0)
        val editor = sharedPreference.edit()
        editor.putInt("count", count)
        editor.apply()
    }
}