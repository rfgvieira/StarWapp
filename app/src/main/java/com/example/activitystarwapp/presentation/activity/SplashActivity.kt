package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.activitystarwapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val cont = this
        val timer = object : CountDownTimer(2500,1000){
            override fun onTick(p0: Long) { }
            override fun onFinish() {
                val intent = Intent(cont ,MainActivity::class.java)
                startActivity(intent)
            }
        }
        timer.start()
    }
}