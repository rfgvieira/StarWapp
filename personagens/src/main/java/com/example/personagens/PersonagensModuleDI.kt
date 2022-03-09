package com.example.personagens

import com.example.personagens.viewmodel.PersonagensViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class PersonagensModuleDI {
    fun getModules() : Module {
        return module {
            viewModel { PersonagensViewModel() }
        }
    }
}