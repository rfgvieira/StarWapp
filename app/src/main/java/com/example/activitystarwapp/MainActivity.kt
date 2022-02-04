package com.example.activitystarwapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activitystarwapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var characterList : ArrayList<CharacterModel.Result> = ArrayList()
    private var planetList : ArrayList<PlanetsModel.Result> = ArrayList()
    private var starshipList : ArrayList<StarshipModel.Result> = ArrayList()
    private var flag : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var tabLayout = binding.tlMain
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.person).setId(0))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.planet).setId(1))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.nave).setId(2))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                flag = tab?.id ?: -1
                when(tab?.id){
                    0 -> { binding.tvSubtitle.setText(R.string.personagens)
                        if(characterList.size > 0)
                            setUpAdapterCharacter()
                        else
                            getData()}
                    1 -> { binding.tvSubtitle.setText(R.string.planetas)
                        if(planetList.size > 0)
                            setUpAdapterPlanet()
                        else
                            getData()}
                    2 -> { binding.tvSubtitle.setText(R.string.espaconaves)
                        if(starshipList.size > 0)
                            setUpAdapterStarship()
                        else
                            getData()}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        flag = 0
        getData()
    }

    private fun getData(){
        binding.pbLoadingrv.visibility = View.VISIBLE
        val retroFit = RetroFit.getRetrofitInstance("https://swapi.dev/api/")
        val endpoint = retroFit.create(Endpoint ::class.java)

        binding.rvPersonagens.adapter = null

        when(flag) {
            0 -> getCharacter(endpoint)
            1 -> getPlanets(endpoint)
            2 -> getStarships(endpoint)
        }
    }

    private fun getCharacter(endpoint: Endpoint){
        val callback = endpoint.getPeoples()

        callback.enqueue(object  : Callback<CharacterModel.Info> {
            override fun onResponse(call: Call<CharacterModel.Info>, response: Response<CharacterModel.Info>) {
                val model = response.body()
                if(model != null){
                    model.results.forEach {
                        addListCharacter(it)
                    }
                    binding.pbLoadingrv.visibility = View.GONE
                    setUpAdapterCharacter()

                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<CharacterModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    private fun getPlanets(endpoint: Endpoint){
        val callback = endpoint.getPlanets()

        callback.enqueue(object  : Callback<PlanetsModel.Info> {
            override fun onResponse(call: Call<PlanetsModel.Info>, response: Response<PlanetsModel.Info>) {
                val model = response.body()
                if(model != null){
                    model.results.forEach {
                        addListPlanet(it)
                    }
                    binding.pbLoadingrv.visibility = View.GONE
                    setUpAdapterPlanet()

                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<PlanetsModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }

    private fun getStarships(endpoint: Endpoint){
        val callback = endpoint.getStarships()

        callback.enqueue(object  : Callback<StarshipModel.Info> {
            override fun onResponse(call: Call<StarshipModel.Info>, response: Response<StarshipModel.Info>) {
                val model = response.body()
                if(model != null){
                    model.results.forEach {
                        addListStarship(it)
                    }
                    binding.pbLoadingrv.visibility = View.GONE
                    setUpAdapterStarship()

                } else{
                    Log.d("nullApi","API Nula")
                }
            }

            override fun onFailure(call: Call<StarshipModel.Info>, t: Throwable) {
                Log.d("falhou","Deu Ruim")
            }
        })
    }


    private fun addListPlanet(model : PlanetsModel.Result){
        planetList.add(model)
    }

    private fun addListCharacter(model : CharacterModel.Result){
        characterList.add(model)
    }

    private fun addListStarship(model : StarshipModel.Result){
        starshipList.add(model)
    }

    private fun setUpAdapterCharacter(){
        var adapter = CharacterAdapter(characterList,this)
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpAdapterPlanet(){
        var adapter = PlanetAdapter(planetList)
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpAdapterStarship(){
        var adapter = StarshipAdapter(starshipList)
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }



}