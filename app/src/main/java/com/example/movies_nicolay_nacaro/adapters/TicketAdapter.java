package com.example.movies_nicolay_nacaro.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_nicolay_nacaro.MainActivity;
import com.example.movies_nicolay_nacaro.databinding.CustomTicketsBinding;
import com.example.movies_nicolay_nacaro.db.Ticket;
import com.example.movies_nicolay_nacaro.db.tDatabase;
import com.example.movies_nicolay_nacaro.fragments.NowTickets;
import com.example.movies_nicolay_nacaro.models.Movies;

import java.util.ArrayList;
import java.util.List;


public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ItemViewHolder>{

    private Context context = null;
    private List<Ticket> tickets = null;
    CustomTicketsBinding binding;
    tDatabase db;
    public TicketAdapter(Context context){
        db = tDatabase.getDatabase(context);
tickets = db.TicketDAO().getAllEmployees();
        this.context = context;
    }

    @NonNull
    @Override
    public TicketAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TicketAdapter.ItemViewHolder(CustomTicketsBinding.inflate(LayoutInflater.from(context), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ItemViewHolder holder, int position) {
        Ticket currentTick = tickets.get(position);
        holder.bind(context, currentTick);
    }





    @Override
    public int getItemCount() {
        return tickets.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{
        CustomTicketsBinding itemBinding;
        public tDatabase db;
        public ItemViewHolder(@NonNull CustomTicketsBinding binding){
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Ticket current){
            itemBinding.title.setText(current.getMovieTitle());
            itemBinding.scheduled.setText(current.getDate());
            itemBinding.pTickets.setText("Tickets purchased: " +current.getQuantity());
itemBinding.clLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        CharSequence text = "Deleted!";
        int duration = Toast.LENGTH_SHORT;
db = tDatabase.getDatabase(context);
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        String tempT = (String) itemBinding.title.getText();
        int a =0;
        for(Movies m: MainActivity.realMovies){
            if(tempT.compareTo(m.getName())==0){
                a= m.getID();
            }
        }
        Ticket temp =
                db.TicketDAO().getTicketById(a);
        db.TicketDAO().deleteTicket(temp);
       int x= getAdapterPosition();
        TicketAdapter.this.tickets.remove(x);
NowTickets.adapter.notifyDataSetChanged();
    }
});
        }
    }
}
