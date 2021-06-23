package com.thesimplifiedapps.checknot.viewmodels.usecases

import com.thesimplifiedapps.checknot.viewmodels.data.Note
import com.thesimplifiedapps.checknot.viewmodels.repositories.BaseCheckNotRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val repository: BaseCheckNotRepository) {

    operator fun invoke(): Flow<List<Note?>> {
        return repository.getNotes()
    }
}