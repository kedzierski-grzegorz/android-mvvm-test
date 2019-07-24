package com.example.mvvmroom.ui.utils;

import android.widget.NumberPicker;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmroom.data.Note;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter({"bindingAdapter", "bindingLiveData", "lifecycleOwner"})
    public static void setRecyclerViewAdapter(RecyclerView view, final NoteAdapter adapter, LiveData<List<Note>> liveData, LifecycleOwner owner){
        if(adapter != null && liveData != null) {
            view.setAdapter(adapter);
            liveData.observe(owner, new Observer<List<Note>>(){
                @Override
                public void onChanged(List<Note> notes) {
                    adapter.submitList(notes);
                }
            });
        }
    }

    @BindingAdapter("hasFixedSize")
    public static void setHasFixedSize(RecyclerView view, boolean value){
        if(value) {
            view.setHasFixedSize(true);
        }
    }

    @BindingAdapter("minValue")
    public static void setNumberPickerMinValue(NumberPicker view, int value){
        if(value != 0) {
            view.setMinValue(value);
        }
    }

    @BindingAdapter("maxValue")
    public static void setNumberPickerMaxValue(NumberPicker view, int value){
        if(value != 0) {
            view.setMaxValue(value);
        }
    }
}
