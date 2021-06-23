package com.thesimplifiedapps.checknot.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.thesimplifiedapps.checknot.R
import com.thesimplifiedapps.checknot.databinding.ActivityListCheckNotBinding

class ListCheckNotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListCheckNotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CheckNot)
        super.onCreate(savedInstanceState)

        binding = ActivityListCheckNotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noteListFab.setImageResource(R.drawable.ic_add_notes)
        binding.noteListTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> binding.noteListFab.setImageResource(R.drawable.ic_add_notes)
                    1 -> binding.noteListFab.setImageResource(R.drawable.ic_add_checklist)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.noteListFab.setOnClickListener {
            startActivity(Intent(applicationContext, AddCheckNotActivity::class.java).also {
                it.putExtra(AddCheckNotActivity.KEY_TYPE, AddCheckNotActivity.CheckNotType.Note.toString())
            })
        }
    }
}