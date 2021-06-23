package com.thesimplifiedapps.checknot.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var title: String,

    var notes: String,

    var backColor: Int,

    var fontColor: Int,

    var addedTime: Long = System.currentTimeMillis()
)
