package com.thesimplifiedapps.checknot.viewmodels.repositories

import com.thesimplifiedapps.checknot.data.datasource.LocalDataSource
import com.thesimplifiedapps.checknot.viewmodels.data.Note
import kotlinx.coroutines.flow.Flow

abstract class BaseCheckNotRepository: LocalDataSource {

    abstract override suspend fun insertNote(note: Note)

    abstract override fun getNote(noteId: Int): Flow<Note?>

    abstract override fun getNotes(): Flow<List<Note?>>
}