package com.example.mvvmroom.ui.utils;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

public class ObservableViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    public void notifyChange(){
        callbacks.notifyCallbacks(this, 0, null);
    }

    public void notifyPropertyChanged(int fieldId){
        callbacks.notifyCallbacks(this,fieldId,null);
    }
}
