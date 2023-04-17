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
        var count = 0

        countText.text = count.toString()
        //test1
        countBtn.setOnClickListener {
            count += 1
            countText.text = count.toString()
        }
    }
}