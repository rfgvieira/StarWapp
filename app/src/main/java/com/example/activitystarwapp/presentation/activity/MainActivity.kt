package com.example.activitystarwapp.presentation.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.AdapterView
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityMenuBinding
import com.example.activitystarwapp.databinding.PopupBinding
import com.example.activitystarwapp.presentation.adapter.GridAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter()
        binding.gvGridmain.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                if(position != 3)
                    showPopUp(position)
                else {
                    val intent = Intent(this, RandomActivity::class.java)
                    startActivity(intent)
                }

            }


    }

    private fun setUpAdapter(){
        var arrayName = arrayListOf(R.string.espaconaves,R.string.planetas, R.string.personagens, R.string.aleatorio)
        var arrayImage = arrayListOf(R.drawable.starship, R.drawable.planet, R.drawable.luke, R.drawable.darthvader)
        val adapter = GridAdapter(this,arrayName, arrayImage)
        binding.gvGridmain.adapter = adapter
    }
    private fun showPopUp(tipo: Int) {

        val bindingPopupBinding = PopupBinding.inflate(layoutInflater)

        bindingPopupBinding.btnPopuptudo.setOnClickListener {
            redirectActivity(tipo,0)
        }

        bindingPopupBinding.btnPopupid.setOnClickListener {
            redirectActivity(tipo,1)
        }

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(bindingPopupBinding.root)


        val window = dialog.window
        window?.let {
            it.attributes.gravity = Gravity.BOTTOM
            it.attributes.width = this.window.attributes.width
            it.setBackgroundDrawable(getDrawable(R.drawable.roundedbtn))}

        dialog.show()
    }


    private fun redirectActivity(tipo : Int, modo : Int) {
        when(tipo){
            0 -> {
                if(modo == 0) {
                    val intent = Intent(this, TodosStarshipsActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, BuscaStarshipActivity::class.java)
                    startActivity(intent)
                }
            }
            1 -> {
                if(modo == 0) {
                    val intent = Intent(this, TodosPlanetasActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, BuscaPlanetaActivity::class.java)
                    startActivity(intent)
                }
            }
            2 -> {
                if(modo == 0) {
                    val intent = Intent(this, TodosPersonagensActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, BuscaPersonagemActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}