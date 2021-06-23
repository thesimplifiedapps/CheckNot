package com.thesimplifiedapps.checknot.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thesimplifiedapps.checknot.R
import com.thesimplifiedapps.checknot.databinding.ActivityAddCheckNotBinding
import com.thesimplifiedapps.checknot.ui.adapters.BottomMenuAdapter
import com.thesimplifiedapps.checknot.viewmodels.AddCheckNotViewModel

class AddCheckNotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCheckNotBinding
    private lateinit var viewModel: AddCheckNotViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCheckNotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AddCheckNotViewModel::class.java]

        init()

        initObservers()

        initListeners()
    }

    private fun initListeners() {
        binding.buttonLeft.setOnClickListener {
            when ((it as ImageView).tag as String) {
                CLOSE_ACTION -> finish()
                CANCEL_ACTION -> noteViewMode()
            }
        }

        binding.buttonRight.setOnClickListener {
            when ((it as ImageView).tag as String) {
                SAVE_ACTION -> {
                    if (binding.titleTextInput.text?.isNotEmpty()!! || binding.editTextNote.text?.isNotEmpty()!!) {
                        viewModel.currentNote.title = binding.titleTextInput.text.toString()
                        viewModel.currentNote.notes = binding.editTextNote.text.toString()
                        viewModel.currentNote.fontColor = viewModel.selectedFontColor
                        viewModel.currentNote.backColor = viewModel.selectedBackgroundColor
                        viewModel.currentNote.addedTime = System.currentTimeMillis()
                        viewModel.insertNote()
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.empty_fields),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                EDIT_ACTION -> noteEditMode(true)
            }
        }
    }

    private fun initObservers() {
        viewModel.note.observe(this) {
            it?.let {
                viewModel.currentNote = it
                viewModel.selectedFontColor = it.fontColor
                viewModel.selectedBackgroundColor = it.backColor

                noteViewMode()

                binding.titleTextInput.setText(it.title)
                binding.titleTextInput.isFocusable = false
                binding.titleTextInput.isCursorVisible = false

                binding.editTextNote.setText(it.notes)
                binding.editTextNote.isFocusable = false
                binding.editTextNote.isCursorVisible = false

                setFontColor()
                setBackgroundColor()
            } ?: noteEditMode(false)
        }
    }

    private fun init() {
        viewModel.selectedFontColor = ContextCompat.getColor(this, R.color.font_1)
        viewModel.selectedBackgroundColor = ContextCompat.getColor(this, R.color.background_1)

        if (CheckNotType.valueOf(intent.getStringExtra(KEY_TYPE)!!) == CheckNotType.Note) {
            binding.editTextNote.visibility = View.VISIBLE
        }

        setBackgroundColor()
        setFontColor()

        viewModel.getNote(intent.getIntExtra(KEY_ID, DEFAULT_ID))
    }

    private fun initBottomMenu() {
        val backgroundColorList = listOf(
            R.color.background_1, R.color.background_2, R.color.background_3, R.color.background_4,
            R.color.background_5, R.color.background_6, R.color.background_7, R.color.background_8,
            R.color.background_9, R.color.background_10, R.color.background_11,
            R.color.background_12
        )
        val fontColorList = listOf(
            R.color.font_1, R.color.font_2, R.color.font_3, R.color.font_4, R.color.font_5,
            R.color.font_6, R.color.font_7, R.color.font_8, R.color.font_9, R.color.font_10,
            R.color.font_11, R.color.font_12
        )

        binding.textColorChanger.setTextColor(viewModel.selectedFontColor)
        binding.textColorChanger.setOnClickListener {
            binding.linearLayoutBottomMenu.visibility = View.GONE
            binding.recyclerViewBottomMenu.visibility = View.VISIBLE
            initRecyclerView(fontColorList, viewModel.selectedFontColor, true)
        }
        binding.backgroundColorChanger.background.setTint(viewModel.selectedBackgroundColor)
        binding.backgroundColorChanger.setOnClickListener {
            binding.linearLayoutBottomMenu.visibility = View.GONE
            binding.recyclerViewBottomMenu.visibility = View.VISIBLE
            initRecyclerView(backgroundColorList, viewModel.selectedBackgroundColor, false)
        }
    }

    private fun initRecyclerView(colorList: List<Int>, selectedColor: Int, isFontColor: Boolean) {
        binding.recyclerViewBottomMenu.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBottomMenu.adapter =
            BottomMenuAdapter(colorList, selectedColor, isFontColor) { color, isFont ->
                if (isFont) {
                    (binding.recyclerViewBottomMenu.adapter as BottomMenuAdapter).setSelectedColor(
                        color
                    )
                    viewModel.selectedFontColor = color
                    setFontColor()
                } else {
                    (binding.recyclerViewBottomMenu.adapter as BottomMenuAdapter).setSelectedColor(
                        color
                    )
                    viewModel.selectedBackgroundColor = color
                    setBackgroundColor()
                }
            }
    }

    private fun noteEditMode(fromEdit: Boolean) {
        binding.buttonLeft.setColorFilter(ContextCompat.getColor(this, R.color.cancel))
        binding.buttonLeft.tag = if (fromEdit) CANCEL_ACTION else CLOSE_ACTION
        binding.buttonRight.setImageResource(R.drawable.ic_done)
        binding.buttonRight.tag = SAVE_ACTION
        if (binding.buttonShare.isVisible) {
            binding.buttonShare.visibility = View.GONE
        }
        binding.titleTextInput.isFocusable = true
        binding.titleTextInput.isCursorVisible = true
        binding.editTextNote.isFocusable = true
        binding.editTextNote.isCursorVisible = true
        if (!binding.linearLayoutBottomMenu.isVisible) {
            binding.linearLayoutBottomMenu.visibility = View.VISIBLE
        }
        initBottomMenu()
    }

    private fun noteViewMode() {
        binding.buttonLeft.setColorFilter(ContextCompat.getColor(this, R.color.white))
        binding.buttonLeft.tag = CLOSE_ACTION
        binding.buttonRight.setImageResource(R.drawable.ic_edit)
        binding.buttonRight.tag = EDIT_ACTION
        if (!binding.buttonShare.isVisible) {
            binding.buttonShare.visibility = View.VISIBLE
        }
        if (binding.linearLayoutBottomMenu.isVisible) {
            binding.linearLayoutBottomMenu.visibility = View.GONE
        }
    }

    private fun setFontColor() {
        binding.textColorChanger.setTextColor(viewModel.selectedFontColor)
        binding.titleTextInput.setTextColor(viewModel.selectedFontColor)
        if (binding.editTextNote.isVisible) {
            binding.editTextNote.setTextColor(viewModel.selectedFontColor)
        }
    }

    private fun setBackgroundColor() {
        binding.backgroundColorChanger.background.setTint(viewModel.selectedBackgroundColor)
        binding.activityContainer.setBackgroundColor(viewModel.selectedBackgroundColor)
    }

    override fun onBackPressed() {
        if (binding.recyclerViewBottomMenu.isVisible) {
            binding.recyclerViewBottomMenu.visibility = View.GONE
            binding.linearLayoutBottomMenu.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }

    enum class CheckNotType {
        Note,
        CheckList
    }

    companion object {
        const val DEFAULT_ID = -1
        const val KEY_ID = "check_not_id"
        const val KEY_TYPE = "check_not_type"
        const val CANCEL_ACTION = "cancel"
        const val SAVE_ACTION = "save"
        const val CLOSE_ACTION = "close"
        const val EDIT_ACTION = "edit"
    }
}