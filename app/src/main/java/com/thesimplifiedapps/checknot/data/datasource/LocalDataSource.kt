package com.thesimplifiedapps.checknot.data.datasource

import com.thesimplifiedapps.checknot.viewmodels.data.Note
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {

    suspend fun insertNote(note: Note)

    fun getNote(noteId: Int): Flow<Note?>

    fun getNotes(): Flow<List<Note?>>
}