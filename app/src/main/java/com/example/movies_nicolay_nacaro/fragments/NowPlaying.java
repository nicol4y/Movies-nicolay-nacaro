package com.example.movies_nicolay_nacaro.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies_nicolay_nacaro.MainActivity;
import com.example.movies_nicolay_nacaro.adapters.MovieAdapter;
import com.example.movies_nicolay_nacaro.R;
import com.example.movies_nicolay_nacaro.databinding.NowPlayingBinding;
import com.example.movies_nicolay_nacaro.models.Movies;


public class NowPlaying extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static MovieAdapter adapter;
    private NowPlayingBinding binding;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlaying() {
        super(R.layout.now_playing);
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NowPlayingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inflate the layout for this fragment
        adapter = new MovieAdapter(requireActivity(), MainActivity.realMovies);
        binding.recyclerview.setAdapter(adapter);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}