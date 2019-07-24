package com.example.mvvmroom.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmroom.data.Note;
import com.example.mvvmroom.data.NoteRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public MainViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNotes();
    }

    public void add100Notes(){
        noteRepository.add100Notes();
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }
}
