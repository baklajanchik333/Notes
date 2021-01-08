package com.example.notes.Listeners;

import com.example.notes.Entities.Note;

public interface NotesListener {
    void onNoteCLicked(Note note, int position);
}
