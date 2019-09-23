package com.example.myapplicationtest.activies

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.R
import com.example.myapplicationtest.adapter.NoteAdapter
import com.example.myapplicationtest.domain.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notes = mutableListOf<Note>()
    private var noteAdapter = NoteAdapter(notes, this::onNoteItemClick, this::onNoteItemLongClick)

    private fun onNoteItemClick(note: Note) {
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("description", note.description)
        intent.putExtra("title", note.title)
        startActivityForResult(intent, 10)
    }

    private fun onNoteItemLongClick(note: Note): Boolean{

        var positionNote: Int = 0
        for( i in notes.indices){
            if( notes[i] === note){
                positionNote = i
            }
        }

        notes.removeAt(positionNote)
        noteAdapter.notifyItemRemoved(positionNote)


        Toast.makeText(this, "Removendo anotação $note", Toast.LENGTH_SHORT).show()
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initSwipeDelete(){
        val swipe = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                notes.removeAt(position)
                noteAdapter.notifyItemRemoved(position)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)


    }


    fun initRecycler(){
        addNote()
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = noteAdapter
        initSwipeDelete()

    }

    private fun addNote() {
        notes.add( Note("Necessidades", "bolo de chocolates, dormir, orar, ir para a igreja") )
        notes.add( Note("Series", "How i meet your mother") )
        notes.add( Note("Curiosidades", "XX") )
        notes.add( Note("Doces preferidos", "XX") )
        notes.add( Note("Filmes ainda não assistidos", "XXXX") )
        notes.add( Note("Restaurantes", "XXX") )
        notes.add( Note("Livros", "XXXX") )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK){
            data?.let {
                val title: String? = data.getStringExtra("title")
                val newTitle: String? = data.getStringExtra("newTitle")
                val newDescription: String? = data.getStringExtra("newDescription")

                if( newDescription != null && newTitle != null) {

                    var isNewNote: Boolean = true

                    for (i in notes.indices) {
                        if (notes[i].title == title) {
                            notes[i].description = newDescription
                            notes[i].title = newTitle
                            isNewNote = false
                            break
                        }
                    }

                    if ( isNewNote)
                        notes.add(Note(newTitle, newDescription))

                    recyclerViewNotes.adapter = noteAdapter
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, Main2Activity::class.java)
                startActivityForResult(intent, 10)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }




}
