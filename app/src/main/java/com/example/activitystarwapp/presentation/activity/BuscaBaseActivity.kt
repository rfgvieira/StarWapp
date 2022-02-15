package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityBasebuscaBinding

abstract class BuscaBaseActivity : BaseActivity() {
    private lateinit var binding: ActivityBasebuscaBinding
    protected var id : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasebuscaBinding.inflate(layoutInflater)
        binding.imvBuscaicon.setOnClickListener {
            id = binding.edtSearchbar.text.toString()
            searchId()

        }
    }

    override fun setContentView(view: View) {
        binding.flBusca.addView(view)
        super.setContentView(binding.root)
    }

    abstract fun searchId()

}