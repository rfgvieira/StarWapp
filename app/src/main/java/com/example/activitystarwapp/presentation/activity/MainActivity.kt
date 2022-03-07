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
import com.example.espaconave.activity.StarshipActivity
import com.example.personagens.activity.PersonagensActivity
import com.example.planetas.activity.PlanetasActivity
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
                val intent = Intent(this, StarshipActivity::class.java)
                intent.putExtra("modo", modo)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this, PlanetasActivity::class.java)
                intent.putExtra("modo", modo)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this, PersonagensActivity::class.java)
                intent.putExtra("modo",modo)
                startActivity(intent)
            }
        }
    }
}