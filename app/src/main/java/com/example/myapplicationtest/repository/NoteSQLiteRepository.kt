package com.example.applicationdatabase.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.applicationdatabase.*
import com.example.myapplicationtest.domain.Note

class NoteSQLiteRepository(ctx: Context): NoteRepository {
    override fun searchAll(): List<Note> {
        val notes = ArrayList<Note>()
        val db = helper.writableDatabase
        var cursor: Cursor? = null

        cursor = db.rawQuery("select * from $TABLE_NAME", null)

        var id: Long
        var title: String
        var description: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

                notes.add(Note(id, title, description))
                cursor.moveToNext()
            }
        }
        return notes
    }

    private val helper: NoteSqlHelper = NoteSqlHelper(ctx)

    private fun insert(note: Note){
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_DESCRIPTION, note.description)
        }

        val id = db.insert(TABLE_NAME, null, cv)

        if (id != -1L)
            note.id = id

        db.close()

    }

    private fun update(note: Note) {
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_DESCRIPTION, note.description)
        }

        db.update(
            TABLE_NAME,
            cv,
            "$COLUMN_TITLE = ? ",
            arrayOf(note.id.toString())
        )
        db.close()
    }

    override fun save(note: Note) {
        if (note.id === 0L) {
            insert(note)
        } else {
            update(note)
        }
    }

    override fun remove(vararg notes: Note) {
        val db = helper.writableDatabase

        for (note in notes){

            db.delete(
                TABLE_NAME,
                "$COLUMN_TITLE = ? ",
                arrayOf(note.title.toString())
            )
        }
        db.close()
    }


}