package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.activitystarwapp.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        binding.imvVolta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setContentView(view: View) {
        binding.flBaseframe.addView(view)
        super.setContentView(binding.root)
    }



    override fun onResume() {
        super.onResume()
        binding.pbLoading.visibility = View.VISIBLE
    }

    fun setTitleActivity(idRes : Int){
        binding.tvTitle.text = getString(idRes)
    }

    fun setIconActivity(idRes: Int){
        binding.mainicon.setImageResource(idRes)
    }

    fun loadCompleted(){
        binding.pbLoading.visibility = View.GONE
    }



}