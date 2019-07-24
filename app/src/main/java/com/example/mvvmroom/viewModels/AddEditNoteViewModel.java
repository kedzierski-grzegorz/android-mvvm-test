package com.example.mvvmroom.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.InverseMethod;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.mvvmroom.data.Note;
import com.example.mvvmroom.data.NoteRepository;

public class AddEditNoteViewModel extends AndroidViewModel {

    private int id = -1;
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<Integer> priority = new MutableLiveData<>();

    private NoteRepository noteRepository;

    public AddEditNoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        priority.setValue(1);
    }

    public void saveNote() {
        Note note = new Note(title.getValue(), description.getValue(), priority.getValue());

        if (id == -1) {
            noteRepository.insert(note);
        } else {
            note.setId(id);
            noteRepository.update(note);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setDescription(MutableLiveData<String> description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public MutableLiveData<Integer> getPriority() {
        return priority;
    }

    public void setPriority(MutableLiveData<Integer> priority) {
        this.priority = priority;
    }

    public void setPriority(int priority) {
        this.priority.setValue(priority);
    }
}
