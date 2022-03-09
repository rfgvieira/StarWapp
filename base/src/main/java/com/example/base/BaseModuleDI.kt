package com.example.base

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class BaseModuleDI {
    fun getModules() : Module {
        return module {
            viewModel { BaseViewModel() }
        }
    }
}