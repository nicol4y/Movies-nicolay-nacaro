package com.example.movies_nicolay_nacaro.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies_nicolay_nacaro.R;
import com.example.movies_nicolay_nacaro.adapters.TicketAdapter;
import com.example.movies_nicolay_nacaro.databinding.NowTicketsBinding;
import com.example.movies_nicolay_nacaro.db.Ticket;
import com.example.movies_nicolay_nacaro.db.tDatabase;


public class NowTickets extends Fragment {
    private NowTicketsBinding binding;
    private tDatabase db;
    public static TicketAdapter adapter;

    public NowTickets() {
        super(R.layout.now_tickets);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        this.db = tDatabase.getDatabase(getActivity().getApplicationContext());
        adapter = new TicketAdapter(getContext());
        binding = NowTicketsBinding.inflate(inflater, container, false);
        binding.recycTicket.setAdapter(adapter);
        binding.recycTicket.setLayoutManager(new LinearLayoutManager(getContext()));
        if (adapter.getItemCount() == 0) {
            binding.emptyView.setVisibility(View.VISIBLE);
        } else {
            binding.emptyView.setVisibility(View.GONE);
        }
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}