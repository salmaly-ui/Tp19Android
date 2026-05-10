package com.example.notesapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notesapp.data.Note;
import com.example.notesapp.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnAdd, btnDeleteAll;
    private RecyclerView rvNotes;
    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        btnAdd = findViewById(R.id.btn_add);
        btnDeleteAll = findViewById(R.id.btn_delete_all);
        rvNotes = findViewById(R.id.rv_notes);

        // Configuration du RecyclerView
        adapter = new NoteAdapter();
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);

        // Configuration du ViewModel
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Observation des changements
        noteViewModel.getAllNotes().observe(this, notes -> {
            if (notes != null) {
                adapter.setNotes(notes);
            }
        });

        // Gestion des clics sur l'adaptateur
        adapter.setOnNoteClickListener(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Toast.makeText(MainActivity.this, "Titre: " + note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClick(Note note) {
                noteViewModel.delete(note);
                Toast.makeText(MainActivity.this, "Note supprimée", Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton Ajouter
        btnAdd.setOnClickListener(v -> addNote());

        // Bouton Supprimer tout
        btnDeleteAll.setOnClickListener(v -> {
            noteViewModel.deleteAll();
            Toast.makeText(MainActivity.this, "Toutes les notes supprimées", Toast.LENGTH_SHORT).show();
        });
    }

    private void addNote() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir un titre", Toast.LENGTH_SHORT).show();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir une description", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note(title, description);
        noteViewModel.insert(note);

        // Vider les champs
        etTitle.setText("");
        etDescription.setText("");

        Toast.makeText(this, "Note ajoutée", Toast.LENGTH_SHORT).show();
    }
}