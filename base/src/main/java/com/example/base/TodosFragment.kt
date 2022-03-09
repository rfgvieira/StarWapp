package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.base.databinding.FragmentTodosBinding
import com.example.model.CharacterModel
import com.example.model.PlanetsModel
import com.example.model.StarshipModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TodosFragment : Fragment() {

    private lateinit var binding : FragmentTodosBinding
    private val viewModel : BaseViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentTodosBinding.inflate(inflater,container,false)
        return binding.root
    }


    fun setUpAdapter(list : List<*>,) {
         context?.let {
             var adapter : RecyclerView.Adapter<*>? = null
             when (list.firstOrNull()){
                 is PlanetsModel.Result -> adapter = BaseAdapter(it, list as List<PlanetsModel.Result>, viewModel)
                 is CharacterModel.Result -> adapter = BaseAdapter(it, list as List<CharacterModel.Result>, viewModel)
                 is StarshipModel.Result -> adapter = BaseAdapter(it, list as List<StarshipModel.Result>, viewModel)
                 null -> adapter = null
             }
             binding.tvResultcounttodosplanet.text = getString(R.string.resultado) + list.count()
             binding.rvPlanets.adapter = adapter
             binding.rvPlanets.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v("so pra logar", "então....")
    }

    fun clearRv(){
        binding.rvPlanets.adapter = null
    }
}