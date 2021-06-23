package com.thesimplifiedapps.checknot.data.repositories

import com.thesimplifiedapps.checknot.data.CheckNotDatabase
import com.thesimplifiedapps.checknot.data.mapping.toDomainEntity
import com.thesimplifiedapps.checknot.data.mapping.toRoomEntity
import com.thesimplifiedapps.checknot.viewmodels.data.Note
import com.thesimplifiedapps.checknot.viewmodels.repositories.BaseCheckNotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckNotRepository : BaseCheckNotRepository() {

    private val dao = CheckNotDatabase.getDatabase().checkNotDao()

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note.toRoomEntity())
    }

    override fun getNote(noteId: Int): Flow<Note?> {
        return dao.getNote(noteId).map {
            it.toDomainEntity()
        }
    }

    override fun getNotes(): Flow<List<Note?>> {
        return dao.getNotes().map {
            it.map { note ->
                note.toDomainEntity()
            }
        }
    }
}