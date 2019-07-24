package com.example.mvvmroom.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmroom.R;
import com.example.mvvmroom.databinding.FragmentAddEditBinding;
import com.example.mvvmroom.viewModels.AddEditNoteViewModel;

public class AddEditFragment extends Fragment {
    public static final String EXTRA_ID = "com.example.mvvmroom.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.mvvmroom.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mvvmroom.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.mvvmroom.EXTRA_PRIORITY";

    private AddEditNoteViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this).get(AddEditNoteViewModel.class);
        FragmentAddEditBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit, container, false);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.setId(getArguments().getInt(EXTRA_ID));

        if(viewModel.getId() != -1){
            getActivity().setTitle("Edit note");
            viewModel.setTitle(getArguments().getString(EXTRA_TITLE));
            viewModel.setDescription(getArguments().getString(EXTRA_DESCRIPTION));
            viewModel.setPriority(getArguments().getInt(EXTRA_PRIORITY));
        } else {
            getActivity().setTitle("Add note");
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_note_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Navigation.findNavController(getView()).popBackStack();
                break;

            case R.id.save_note:
                viewModel.saveNote();
                Navigation.findNavController(getView()).popBackStack();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
