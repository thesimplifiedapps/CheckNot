package com.thesimplifiedapps.checknot.data.mapping

import com.thesimplifiedapps.checknot.viewmodels.data.Note

fun Note.toRoomEntity(): com.thesimplifiedapps.checknot.data.entities.Note {
    return com.thesimplifiedapps.checknot.data.entities.Note(id, title, notes, backColor, fontColor, addedTime)
}