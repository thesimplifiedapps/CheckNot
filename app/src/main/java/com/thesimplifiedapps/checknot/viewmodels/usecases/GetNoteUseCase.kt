package com.thesimplifiedapps.checknot.viewmodels.usecases

import com.thesimplifiedapps.checknot.viewmodels.data.Note
import com.thesimplifiedapps.checknot.viewmodels.repositories.BaseCheckNotRepository
import kotlinx.coroutines.flow.Flow

class GetNoteUseCase(private val repository: BaseCheckNotRepository) {

    operator fun invoke(noteId: Int): Flow<Note?> {
        return repository.getNote(noteId)
    }
}