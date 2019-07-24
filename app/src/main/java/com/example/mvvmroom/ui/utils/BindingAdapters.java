package com.example.mvvmroom.ui.utils;

import android.widget.NumberPicker;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

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
