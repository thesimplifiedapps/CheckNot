package com.thesimplifiedapps.checknot

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
    }

    companion object {
        lateinit var myApplicationContext: MyApplication
    }
}