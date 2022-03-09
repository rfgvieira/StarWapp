package com.example.espaconave

import com.example.espaconave.viewmodel.StarshipsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class EspacoNaveModuleDI {
    fun getModules() : Module {
        return module {
            viewModel { StarshipsViewModel() }
        }
    }
}