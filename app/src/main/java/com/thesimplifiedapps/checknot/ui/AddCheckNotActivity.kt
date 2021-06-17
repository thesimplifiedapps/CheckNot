package com.thesimplifiedapps.checknot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thesimplifiedapps.checknot.databinding.ActivityAddCheckNotBinding

class AddCheckNotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCheckNotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCheckNotBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}