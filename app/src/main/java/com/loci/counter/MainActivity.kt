package com.loci.counter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var count = 0

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as Vibrator
        } else {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        val countBtn = findViewById<Button>(R.id.countBtn)
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        val countText = findViewById<TextView>(R.id.countText)
        val resetBtn = findViewById<ImageButton>(R.id.resetBtn)
        val toggleBtn = findViewById<SwitchCompat>(R.id.vibrateSwitch)

        val sharedPreference = getSharedPreferences("prf", 0)
        val editor = sharedPreference.edit()

        count = sharedPreference.getInt("count", 0)

        countText.text = count.toString()

        fun onPressVibrate() {
            // API level 26
            if (sharedPreference.getBoolean("vibrate", false)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            1,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator.vibrate(1)
                }
            }
        }


        countBtn.setOnClickListener {
            count++
            countText.text = count.toString()
            onPressVibrate()
        }

        minusBtn.setOnClickListener {
            count--
            countText.text = count.toString()
            onPressVibrate()
        }

        resetBtn.setOnClickListener {
            count = 0
            countText.text = count.toString()
            onPressVibrate()
        }


        toggleBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean("vibrate", true)
                editor.apply()
            } else {
                editor.putBoolean("vibrate", false)
                editor.apply()
            }

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