package com.example.activitystarwapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.content.res.AppCompatResources
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityMenuBinding
import com.example.activitystarwapp.databinding.PopupBinding
import com.example.activitystarwapp.databinding.PopupRandBinding
import com.example.activitystarwapp.presentation.adapter.GridAdapter
import com.example.espaconave.presentation.activity.BuscaStarshipActivity
import com.example.espaconave.presentation.activity.TodosStarshipsActivity
import com.example.personagem.presentation.activity.BuscaPersonagemActivity
import com.example.personagem.presentation.activity.TodosPersonagensActivity
import com.example.planetas.presentation.activity.BuscaPlanetaActivity
import com.example.planetas.presentation.activity.TodosPlanetasActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

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
                showPopUpRand()
            }
        }
    }

    private fun setUpAdapter(){
        val arrayName = arrayListOf(R.string.espaconaves,R.string.planetas, R.string.personagens, R.string.aleatorio)
        val arrayImage = arrayListOf(R.drawable.starship, R.drawable.planet, R.drawable.luke, R.drawable.darthvader)
        val adapter = GridAdapter(this,arrayName, arrayImage)
        binding.gvGridmain.adapter = adapter
    }

    private fun showPopUpRand(){
        val bindingPopupBinding = PopupRandBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog(this)
        dialog.setCancelable(true)
        val window = dialog.window
        window?.let {
            it.setBackgroundDrawable(getDrawable(R.drawable.popupbg))}
        dialog.setContentView(bindingPopupBinding.root)

        bindingPopupBinding.btnPopuplista.setOnClickListener {
            val rand = (0..2).random()
            redirectActivity(rand,0)
            dialog.dismiss()
        }

        bindingPopupBinding.btnPopupitem.setOnClickListener {
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showPopUp(tipo: Int) {
        val bindingPopupBinding = PopupBinding.inflate(layoutInflater)



        val dialog = BottomSheetDialog(this)
        dialog.setCancelable(true)
        val window = dialog.window
        window?.let {
            it.setBackgroundDrawable(AppCompatResources.getDrawable(this,R.drawable.popupbg))}
        dialog.setContentView(bindingPopupBinding.root)

        bindingPopupBinding.btnPopuptudo.setOnClickListener {
            redirectActivity(tipo,0)
            dialog.dismiss()
        }

        bindingPopupBinding.btnPopupid.setOnClickListener {
            redirectActivity(tipo,1)
            dialog.dismiss()
        }

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