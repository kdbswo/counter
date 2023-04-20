package com.loci.counter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countBtn = findViewById<Button>(R.id.countBtn)
        val countText = findViewById<TextView>(R.id.countText)

        val sharedPreference = getSharedPreferences("number", 0)
        val editor = sharedPreference.edit()
        val saveNumber = sharedPreference.getInt("count", 0).toString()
        countText.text = saveNumber
        var count = saveNumber.toInt()

        countBtn.setOnClickListener {
            count += 1
            countText.text = count.toString()
            editor.putInt("count", count)
            editor.apply()
        }
    }
}