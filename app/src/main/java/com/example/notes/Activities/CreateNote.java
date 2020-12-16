package com.example.notes.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.Database.NotesDatabase;
import com.example.notes.Entities.Note;
import com.example.notes.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {
    private ImageView back, save;
    private EditText noteTitle, noteSubtitle, note;
    private TextView dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        noteTitle = findViewById(R.id.noteTitle);
        noteSubtitle = findViewById(R.id.noteSubtitle);
        note = findViewById(R.id.note);
        dateTime = findViewById(R.id.dateTime);

        back.setOnClickListener(v -> {
            onBackPressed();
        });

        save.setOnClickListener(v -> {
            saveNote();
        });

        dateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault())
                .format(new Date()));
    }

    private void saveNote() {
        if (noteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.noEmptyTitleNote, Toast.LENGTH_SHORT).show();
        } else if (note.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.noEmptyNote, Toast.LENGTH_SHORT).show();
        }

        final Note note1 = new Note();
        note1.setTitle(noteTitle.getText().toString().trim());
        note1.setSubtitle(noteSubtitle.getText().toString().trim());
        note1.setNoteText(note.getText().toString().trim());
        note1.setDateTime(dateTime.getText().toString().trim());

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note1);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        new SaveNoteTask().execute();
    }
}