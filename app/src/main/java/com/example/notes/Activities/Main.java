package com.example.notes.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.Database.NotesDatabase;
import com.example.notes.Entities.Note;
import com.example.notes.R;

import java.util.List;

public class Main extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addNoteMain = findViewById(R.id.addNoteMain);

        addNoteMain.setOnClickListener(v -> startActivityForResult(new Intent(getApplicationContext(), CreateNote.class), REQUEST_CODE_ADD_NOTE));

        getNotes();
    }

    private void getNotes() {
        @SuppressLint("StaticFieldLeak")
        class GetNoteTask extends AsyncTask<Void, Void, List<Note>> {
            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                Log.d("My_notes", notes.toString().trim());
            }
        }

        new GetNoteTask().execute();
    }
}