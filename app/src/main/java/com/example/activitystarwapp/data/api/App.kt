package com.example.activitystarwapp.data.api

import android.app.Application
import com.example.base.BaseModuleDI
import com.example.espaconave.EspacoNaveModuleDI
import com.example.personagens.PersonagensModuleDI
import com.example.planetas.PlanetsModuleDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate(){
        super.onCreate()

        val modList = listOf( AppModuleDI().getModules(),
            BaseModuleDI().getModules(),
            EspacoNaveModuleDI().getModules(),
            PersonagensModuleDI().getModules(),
            PlanetsModuleDI().getModules() )

        startKoin{
            androidLogger(  )
            androidContext(this@App)
            modules(modList)
        }
    }
}