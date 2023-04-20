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
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        val countText = findViewById<TextView>(R.id.countText)
        val resetBtn = findViewById<TextView>(R.id.resetBtn)
        var count = 0

        countText.text = count.toString()
        countBtn.setOnClickListener {
            count += 1
            countText.text = count.toString()
        }

        minusBtn.setOnClickListener {
            count -= 1
            countText.text = count.toString()
        }


        resetBtn.setOnClickListener {
            count = 0
            countText.text = count.toString()
        }

    }
}