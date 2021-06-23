package com.thesimplifiedapps.checknot.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesimplifiedapps.checknot.data.repositories.CheckNotRepository
import com.thesimplifiedapps.checknot.viewmodels.data.Note
import com.thesimplifiedapps.checknot.viewmodels.usecases.GetNoteUseCase
import com.thesimplifiedapps.checknot.viewmodels.usecases.InsertNoteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddCheckNotViewModel : ViewModel() {

    private val repository = CheckNotRepository()
    private val insertNoteUC = InsertNoteUseCase(repository)
    private val getNoteUC = GetNoteUseCase(repository)

    val note = MutableLiveData<Note>()
    var selectedFontColor = 0
    var selectedBackgroundColor = 0
    var currentNote = Note("", selectedFontColor).also {
        it.title = ""
        it.backColor = selectedBackgroundColor
    }

    fun insertNote() {
        viewModelScope.launch {
            insertNoteUC(currentNote)
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            getNoteUC(noteId).collect {
                note.value = it
            }
        }
    }
}