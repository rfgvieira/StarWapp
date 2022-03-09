package com.example.espaconave.activity


import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseActivity
import com.example.base.ItemFragment
import com.example.base.TodosFragment
import com.example.espaconave.R
import com.example.espaconave.databinding.ActivityEspaconaveBinding
import com.example.espaconave.viewmodel.StarshipsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipActivity : BaseActivity() {

    private lateinit var binding : ActivityEspaconaveBinding
    private val viewModel: StarshipsViewModel by viewModel()
    private lateinit var todosFragment: TodosFragment
    private var modo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modo = intent.getIntExtra("modo", 0)

        binding = ActivityEspaconaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initlizeViews()

        if(modo == 0)
            hideSearch()

        viewModel.setUpList()

        setUpTodosFragment()
        initObservers()
    }

     fun setUpTodosFragment() {
        todosFragment = TodosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_starship,todosFragment,"TodosStarship")
            .commit()
    }

    override fun onResume() {
        super.onResume()
        todosFragment.clearRv()
        getData()
    }

    private fun initlizeViews() {
        setTitleActivity(R.string.espaconaves)
        setIconActivity(R.drawable.starship)
    }


    private fun getData() {
        loadStart()
        val timer = object : CountDownTimer(2500,1000){
            override fun onTick(p0: Long) { }
            override fun onFinish() {
                viewModel.getStarships()
            }
        }
        timer.start()

    }

    private fun initObservers() {
        viewModel.starshipList.observe(this) {
            todosFragment.setUpAdapter(it.results)
            loadCompleted()
        }
    }

    override fun setUpItemFragment(position: Int) {
        viewModel.starshipList.value?.let {
            val fragmentItem = ItemFragment(position, it.results)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_starship, fragmentItem, "ItemPlanetas")
                .commit()
        }
    }

    override fun searchId() {
        if(modo == 1){
            viewModel.starshipList.value?.let {
                if(id.isEmpty()){
                    Toast.makeText(this, R.string.avisonulo, Toast.LENGTH_LONG)
                } else if(id.toInt() > it.results.size ){
                    Toast.makeText(this, R.string.avisoforarange, Toast.LENGTH_LONG)
                }  else {
                    todosFragment.setUpAdapter(listOf(it.results[id.toInt()-1]))
                }
            }
        }
    }

}