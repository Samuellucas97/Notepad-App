package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.domain.Note
import com.example.notes.R
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter (
    private val notes: List<Note>,
    private val callback: (Note) -> Unit
): RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: TextView = itemView.txtTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val note = notes[viewHolder.adapterPosition]
            callback(note)
        }

        return viewHolder

    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (title, text) = notes[position]

        holder.txtTitle.text = title
    }

}