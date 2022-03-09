package com.example.activitystarwapp.data.api

import com.example.activitystarwapp.presentation.viewmodel.RandomViewModel
import com.example.base.BaseViewModel
import com.example.espaconave.viewmodel.StarshipsViewModel
import com.example.personagens.viewmodel.PersonagensViewModel
import com.example.planetas.viewmodel.PlanetasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class AppModuleDI{
    fun getModules() : Module {
        return module {
            viewModel { RandomViewModel() }
        }
    }
}

