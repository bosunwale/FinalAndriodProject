package ca.johnoluwale.contactme;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * These interface class contains the
 * SQL queries for the application. There
 * are four methods that serves as the queries
 * INSERT -> to store a contact
 * DELETE -> to delete a specific contact
 * UPDATE -> to edit and save new information for a specific contaact
 * SELECT -> to display list of contact to the user
 */
@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Query("SELECT * FROM contact ORDER BY `Contact Name`ASC")
    List<Contact> getAllContact();
}
