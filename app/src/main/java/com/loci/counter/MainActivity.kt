package com.loci.counter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as Vibrator
        } else {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        val sharedPreference = getSharedPreferences("vibrate", 0)
        val editor = sharedPreference.edit()

        val countBtn = findViewById<Button>(R.id.countBtn)
        val countText = findViewById<TextView>(R.id.countText)
        val toggleBtn = findViewById<SwitchCompat>(R.id.vibrateSwitch)

        var count = 0
        countText.text = count.toString()

        fun onPressPlusBtn() {
            count += 1
            countText.text = count.toString()
        }

        fun onPressVibrate() {
            // API level 26
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

        toggleBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean("vibrate", true)
                editor.apply()
            } else {
                editor.putBoolean("vibrate", false)
                editor.apply()
            }

        }

        countBtn.setOnClickListener {
            onPressPlusBtn()
            if (sharedPreference.getBoolean("vibrate", false)) {
                onPressVibrate()
            }
        }
    }
}
//val sharedPreference = getSharedPreferences("number", 0)
//        val editor = sharedPreference.edit()
//        val saveNumber = sharedPreference.getInt("count", 0).toString()
//        countText.text = saveNumber
//        var count = saveNumber.toInt()
//
//        countBtn.setOnClickListener {
//            count += 1
//            countText.text = count.toString()
//            editor.putInt("count", count)
//            editor.apply()