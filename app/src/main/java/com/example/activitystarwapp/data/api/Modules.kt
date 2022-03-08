package com.example.activitystarwapp.data.api

import com.example.activitystarwapp.presentation.activity.MainActivity
import com.example.activitystarwapp.presentation.activity.RandomActivity
import com.example.activitystarwapp.presentation.viewmodel.RandomViewModel
import com.example.espaconave.activity.StarshipActivity
import com.example.espaconave.viewmodel.StarshipsViewModel
import com.example.personagens.activity.PersonagensActivity
import com.example.personagens.viewmodel.PersonagensViewModel
import com.example.planetas.activity.PlanetasActivity
import com.example.planetas.viewmodel.PlanetasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { RandomViewModel() }
}

val personagensModules = module {
    viewModel { PersonagensViewModel() }
}

val espaconaveModules = module {
    viewModel { StarshipsViewModel() }
}

val planetsModule = module {
    viewModel { PlanetasViewModel() }
}
