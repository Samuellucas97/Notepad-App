package com.example.trabalho_unidadeii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var notes = mutableListOf<Note>()
    private var notesAdapter = NoteAdapter(notes, this::onNoteItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        


    }

    private fun initRecyclerView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun onNoteItemClick( note : Note) {
        var s = "${note.title} \n ${note.description}"
        Toast.makeText( this, s , Toast.LENGTH_SHORT).show()
    }


}
