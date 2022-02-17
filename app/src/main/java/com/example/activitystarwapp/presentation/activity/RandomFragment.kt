package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.FragmentRandomBinding

class RandomFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomBinding.inflate(inflater, container, false)
        binding.cvRandomitem.visibility = View.GONE
        return binding.root
    }

     fun bindValues(name: String, attr1 : String, attr2 : String, rand: Int, item :List<*>) {
        binding.cvRandomitem.visibility = View.VISIBLE
        var attr1Name = ""
        var attr2Name = ""
        var attr1Extra = ""
        with(activity as RandomActivity){
             when(rand){
                 0 -> {
                     setTitleActivity(R.string.personagens)
                     setIconActivity(R.drawable.luke)
                     binding.imvRandicon.setImageResource(R.drawable.luke)
                     attr1Name = "Altura: "
                     attr1Extra = " m"
                     attr2Name = "Cor dos Olhos: "}
                 1 -> {
                     setTitleActivity(R.string.planetas)
                     setIconActivity(R.drawable.planet)
                     binding.imvRandicon.setImageResource(R.drawable.planet)
                     attr1Name = "Diametro: "
                     attr1Extra = " km"
                     attr2Name = "População: "}
                 2 -> {
                     setTitleActivity(R.string.espaconaves)
                     setIconActivity(R.drawable.starship)
                     binding.imvRandicon.setImageResource(R.drawable.starship)
                     attr1Name = "Tripulação: "
                     attr2Name = "Passageiros: "}
             }
        }


        binding.tvRandname.text = name
        if (rand == 0 )
            binding.tvRandatr1.text = attr1Name + (attr1.toDouble()/100).toString() + attr1Extra
        else
            binding.tvRandatr1.text = attr1Name + attr1+ attr1Extra
        binding.tvRandatr2.text = attr2Name + attr2

        binding.cvRandomitem.setOnClickListener {
            (activity as RandomActivity).setUpItemFragment(item)
        }
    }


}