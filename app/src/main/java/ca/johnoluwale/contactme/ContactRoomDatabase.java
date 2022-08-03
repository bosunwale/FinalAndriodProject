package ca.johnoluwale.contactme;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Room Database for Contact Application
 */

//Annotating the class to be a Room Database
@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    //Defining the Dao that will work with the database
    public abstract ContactDao contactDao();

    /*
    Creating a singleton for the room database to avoid
    multiple instances of the database opened at the same time
     */
    private static ContactRoomDatabase INSTANCE;
    public static ContactRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if (INSTANCE == null){
                    //TODO: Create Database
                    //Creates room database object using Room's database builder
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database")
                             //Implementing a non-destructive Migration Strategy
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
