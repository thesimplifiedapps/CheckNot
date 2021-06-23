package com.thesimplifiedapps.checknot.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thesimplifiedapps.checknot.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckNotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    fun getNote(noteId: Int): Flow<Note>

    @Query("SELECT * FROM notes_table")
    fun getNotes(): Flow<List<Note>>
}