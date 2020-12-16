package com.example.notes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Entities.Note;
import com.example.notes.R;

import java.util.List;

public class Notes extends RecyclerView.Adapter<Notes.NoteViewHolder> {
    private List<Note> notes;

    public Notes(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title, subtitle, dateTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            dateTime = itemView.findViewById(R.id.dateTime);
        }

        void setNote(Note note) {
            title.setText(note.getTitle());
            dateTime.setText(note.getDateTime());
            if (note.getSubtitle().trim().isEmpty()) {
                subtitle.setVisibility(View.GONE);
            } else {
                subtitle.setText(note.getSubtitle());
            }
        }
    }
}
