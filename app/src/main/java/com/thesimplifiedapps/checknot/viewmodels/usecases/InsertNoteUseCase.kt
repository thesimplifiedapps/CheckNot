package com.thesimplifiedapps.checknot.viewmodels.usecases

import com.thesimplifiedapps.checknot.viewmodels.data.Note
import com.thesimplifiedapps.checknot.viewmodels.repositories.BaseCheckNotRepository

class InsertNoteUseCase(private val repository: BaseCheckNotRepository) {

    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}