package com.example.mvvmroom.viewModels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmroom.ui.utils.ObservableViewModel;

public class AddEditNoteViewModel extends ObservableViewModel {
    private String title;
    private String description;
    private int priority = 1;

 

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        notifyPropertyChanged(BR.priority);
    }
}
