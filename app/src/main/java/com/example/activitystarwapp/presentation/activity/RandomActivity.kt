package com.example.activitystarwapp.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.activitystarwapp.R
import com.example.activitystarwapp.databinding.ActivityRandomBinding
import com.example.activitystarwapp.presentation.viewmodel.RandomViewModel
import com.example.base.BaseActivity
import com.example.base.ItemFragment

class RandomActivity : BaseActivity() {
    private lateinit var binding: ActivityRandomBinding
    private lateinit var viewModel: RandomViewModel
    private lateinit var fragmentRandomFragment: RandomFragment
    private val rand = (0..2).random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSearch()
        initializeView()

        viewModel = ViewModelProvider(this).get(RandomViewModel::class.java)

        fragmentRandomFragment = RandomFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_random,fragmentRandomFragment,"Random")
            .commit()

        setUpActivity(rand)
    }

    private fun initializeView() {
        setTitleActivity(R.string.aleatorio)
        setIconActivity(R.drawable.darthvader)
    }

    private fun setUpActivity(rand: Int) {
        when(rand){
            0 -> {
                initObserveCharacter()
                viewModel.getCharacter() }
            1 -> {
                initObservePlanet()
                viewModel.getPlanet() }
            2 -> {
                initObserveStarship()
                viewModel.getStarship() }
        }
    }

    private fun initObserveCharacter() {
        viewModel.characterList.observe(this, {
            fragmentRandomFragment.bindValues(it.name, it.height, it.eye_Color,rand,listOf(it))
            loadCompleted()
        })
    }

    private fun initObservePlanet() {
        viewModel.planetList.observe(this, {
            fragmentRandomFragment.bindValues(it.name, it.diameter, it.population,rand,listOf(it))
            loadCompleted()
        })
    }

    private fun initObserveStarship() {
        viewModel.starshipList.observe(this, {
            fragmentRandomFragment.bindValues(it.name, it.crew, it.passengers,rand,listOf(it))
            loadCompleted()
        })
    }

    fun setUpItemFragment(item :List<*>) {
        val fragmentItem = ItemFragment(-1, item)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_random, fragmentItem, "ItemPlanetas")
            .commit()
    }

    override fun searchId() { }
}