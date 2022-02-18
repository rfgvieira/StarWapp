package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.activitystarwapp.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBaseBinding
    protected var id : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)

        binding.imvBuscaicon.setOnClickListener {
            id = binding.edtSearchbar.text.toString()
            searchId()
        }

        binding.imvVolta.setOnClickListener {
            volta()
        }
    }

     open fun volta() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        volta()
    }

    override fun setContentView(view: View) {
        binding.flBaseframe.addView(view)
        super.setContentView(binding.root)
    }


    fun setTitleActivity(idRes : Int){
        binding.tvTitle.text = getString(idRes)
    }

    fun setTitleActivity(text : String){
        binding.tvTitle.text = text
    }

    fun setIconActivity(idRes: Int){
        binding.mainicon.setImageResource(idRes)
    }

    fun loadStart(){
        binding.pbLoading.visibility = View.VISIBLE
    }

    fun loadCompleted(){
        binding.pbLoading.visibility = View.GONE
    }

    abstract fun searchId()

    fun hideSearch(){
        binding.llBusca.visibility = View.GONE
    }

}