package com.example.planetas

import com.example.planetas.viewmodel.PlanetasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class PlanetsModuleDI {
    fun getModules() : Module {
        return module {
            viewModel { PlanetasViewModel() }
        }
    }
}