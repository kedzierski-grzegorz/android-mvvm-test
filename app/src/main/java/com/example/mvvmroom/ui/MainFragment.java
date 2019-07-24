package com.example.mvvmroom.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmroom.R;
import com.example.mvvmroom.data.Note;
import com.example.mvvmroom.viewModels.MainViewModel;
import com.example.mvvmroom.viewModels.NoteViewModel;
import com.example.mvvmroom.ui.utils.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainFragment extends Fragment {

    private Context context;

    private MainViewModel viewModel;

    private FloatingActionButton btnAddNewNote;

    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        getActivity().setTitle("MVVM Room");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        btnAddNewNote = view.findViewById(R.id.add_note_button);
        btnAddNewNote.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_addEditFragment));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note deletedNote = adapter.getNoteAt(viewHolder.getAdapterPosition());
                viewModel.delete(deletedNote);
                Toast.makeText(context, "Note " + deletedNote.getTitle() + " was deleted",
                        Toast.LENGTH_LONG)
                        .show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
               Bundle bundle = new Bundle();
               bundle.putInt(AddEditFragment.EXTRA_ID, note.getId());
               bundle.putString(AddEditFragment.EXTRA_TITLE, note.getTitle());
               bundle.putString(AddEditFragment.EXTRA_DESCRIPTION, note.getDescription());
               bundle.putInt(AddEditFragment.EXTRA_PRIORITY, note.getPriority());

               Navigation.findNavController(getView())
                       .navigate(R.id.action_mainFragment_to_addEditFragment, bundle);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                viewModel.deleteAllNotes();
                Toast.makeText(context, "All notes deleted", Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.add_100_notes:
                viewModel.add100Notes();
                Toast.makeText(context, "100 notes were added", Toast.LENGTH_LONG)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
