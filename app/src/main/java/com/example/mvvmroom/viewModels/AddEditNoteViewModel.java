package com.example.mvvmroom.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddEditNoteViewModel extends ViewModel {

    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<Integer> priority = new MutableLiveData<>();

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setDescription(MutableLiveData<String> description) {
        this.description = description;
    }

    public MutableLiveData<Integer> getPriority() {
        return priority;
    }

    public void setPriority(MutableLiveData<Integer> priority) {
        this.priority = priority;
    }
}
