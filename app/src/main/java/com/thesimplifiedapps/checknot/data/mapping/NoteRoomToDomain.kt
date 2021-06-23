package com.thesimplifiedapps.checknot.data.mapping

import com.thesimplifiedapps.checknot.data.entities.Note

fun Note?.toDomainEntity(): com.thesimplifiedapps.checknot.viewmodels.data.Note? {
    this?.let { note ->
        return com.thesimplifiedapps.checknot.viewmodels.data.Note(
            note.notes, note.fontColor
        ).also {
            it.id = note.id
            it.title = note.title
            it.backColor = note.backColor
            it.addedTime = note.addedTime
        }
    }?: return null
}