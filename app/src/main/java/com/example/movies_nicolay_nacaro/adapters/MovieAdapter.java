package com.example.movies_nicolay_nacaro.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import com.example.movies_nicolay_nacaro.MainActivity;
import com.example.movies_nicolay_nacaro.databinding.CustomRowsBinding;
import com.example.movies_nicolay_nacaro.db.Ticket;
import com.example.movies_nicolay_nacaro.db.tDatabase;
import com.example.movies_nicolay_nacaro.fragments.NowTickets;
import com.example.movies_nicolay_nacaro.models.Movies;

import java.util.ArrayList;
import java.util.Calendar;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {
    private Context context;
    private ArrayList<Movies> movieList;
    CustomRowsBinding binding;

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        this.movieList = movies;
        this.context = context;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowsBinding.inflate(LayoutInflater.from(context), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movies currentMov = movieList.get(position);
        holder.bind(context, currentMov);
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CustomRowsBinding itemBinding;
        public tDatabase db;

        public ItemViewHolder(@NonNull CustomRowsBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;

        }

        public void bind(Context context, Movies current) {
            db = tDatabase.getDatabase(context);
            itemBinding.title.setText(current.getName());
            if(current.getDesc().length()>320) {
                itemBinding.desc.setText(current.getDesc().substring(0,320).concat("..."));
            }
            else{
                itemBinding.desc.setText(current.getDesc());

            }
            itemBinding.rating.setText(current.getRating() + "%");
            itemBinding.release.setText(current.getReleased());
            itemBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    DatePickerDialog dpd = new DatePickerDialog(context, (datePicker, year1, month1, i2) -> {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                                (view, hourOfDay, minute1) -> {
                                    CharSequence text = "Ticket purchased";
                                    int duration = Toast.LENGTH_SHORT;
                                    String b = "Scheduled for " + (month1 + 1) + "/" + day + " at " + hour + ":" + minute1;
                                    Toast toast = Toast.makeText(context, text, duration);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("You already have a ticket for this movie, you can delete the existing ticket to make space for this one");
                                    builder.setTitle("A ticket already exists");
                                    builder.setPositiveButton("Ok", (dialog, which) -> {
                                        dialog.cancel();
                                    });
                                    final NumberPicker numberPicker = new NumberPicker(context);
                                    numberPicker.setMaxValue(50);
                                    numberPicker.setMinValue(1);
                                    final int[] tickNum = {0};

                                    AlertDialog.Builder built = new AlertDialog.Builder(context);
                                    built.setView(numberPicker);
                                    built.setTitle("Number of tickets to buy");
                                    built.setMessage("");
                                    built.setPositiveButton("Done", (dialog, which)-> {
                                        tickNum[0] = numberPicker.getValue();
                                        int a = 0;
                                        String tempT = (String) itemBinding.title.getText();
                                        for (Movies m : MainActivity.realMovies) {
                                            if (tempT.compareTo(m.getName()) == 0) {
                                                a = m.getID();
                                            }
                                        }
                                        Ticket temp = db.TicketDAO().getTicketById(a);

                                        if (temp == null) {
                                            Ticket newTick = new Ticket(tempT, a, tickNum[0], b);
                                            db.TicketDAO().insertEmployee(newTick);
                                            toast.show();
                                        } else if (temp.getDate().compareTo(b) != 0) {
                                            builder.show();
                                        } else {
                                            temp.addTick();
                                            db.TicketDAO().updateTicket(temp);
                                            toast.show();
                                        }

                                        dialog.cancel();
                                    });
                                    built.create();
                                    built.show();

                                }, hour, minute, false);

                        timePickerDialog.show();


                    }, year, month, day);
                    dpd.show();

                }
            });
            Glide.with(context).load(current.getUrl()).into(itemBinding.icon);
        }
    }
}
