package ca.johnoluwale.contactme;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

/**
 * Table creation for SQLite database
 */
@Entity(tableName = "contact")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private int cid;
    @ColumnInfo(name = "Contact Name")
    private String contactName;
    @ColumnInfo(name = "Contact Email")
    private String contactEmail;
    @ColumnInfo(name = "Contact MobileNumber")
    private String contactNumber;

    /**
     * Constructor
     */
    public Contact(@NonNull String name, @NonNull String email, @NonNull String mobileNumber){
        this.contactName = name;
        this.contactEmail = email;
        this.contactNumber = mobileNumber;
    }

    //getter methods for the declared variables
    public int getId() {
        return cid;
    }
    public String getContactName(){
        return contactName;
    }
    public String getContactEmail(){
        return contactEmail;
    }
    public String getContactNumber(){return contactNumber; }
}
