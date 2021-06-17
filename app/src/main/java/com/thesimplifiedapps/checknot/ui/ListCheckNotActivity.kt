package com.thesimplifiedapps.checknot.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.thesimplifiedapps.checknot.R
import com.thesimplifiedapps.checknot.databinding.ActivityListCheckNotBinding

class ListCheckNotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListCheckNotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CheckNot)
        super.onCreate(savedInstanceState)

        binding = ActivityListCheckNotBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.noteListFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}