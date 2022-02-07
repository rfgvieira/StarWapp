package com.example.activitystarwapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitystarwapp.databinding.ActivityMenuBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscaid.setOnClickListener {
            val intent = Intent(this, BuscaActivity :: class.java)
            startActivity(intent)
        }
        binding.btnBuscatudo.setOnClickListener {
            val intent = Intent(this, TodosActivity :: class.java)
            startActivity(intent)
        }
    }
}