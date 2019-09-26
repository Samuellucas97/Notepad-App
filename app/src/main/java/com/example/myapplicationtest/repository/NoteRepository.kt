package com.example.applicationdatabase.repository

import com.example.myapplicationtest.domain.Note

interface NoteRepository {

    fun save(note: Note)
    fun remove(vararg notes: Note)
    fun searchAll(): List<Note>

}