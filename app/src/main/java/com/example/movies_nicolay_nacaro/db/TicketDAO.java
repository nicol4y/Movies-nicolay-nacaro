package com.example.movies_nicolay_nacaro.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TicketDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertEmployee(Ticket tckt);
    @Query("SELECT * FROM tickets_table")
    public List<Ticket> getAllEmployees();
    @Query("SELECT * FROM tickets_table WHERE movieID = :tempId")
    public Ticket  getTicketById(int tempId);

    @Update
    public void updateTicket(Ticket ticketToUpdate);

    @Delete
    public void deleteTicket(Ticket ticketToDelete);

}
