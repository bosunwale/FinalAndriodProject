package ca.johnoluwale.contactme;


//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;
//
//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Table creation for SQLite database
 */
@Entity(tableName = "contact")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "Contact Name")
    private String contactName;
    @ColumnInfo(name = "Contact Email")
    private String contactEmail;
    @ColumnInfo(name = "Contact MobileNumber")
    private String contactNumber;

    /**
     * Constructor
     */
    public Contact(@NonNull String contactName, @NonNull String contactEmail, @NonNull String contactNumber){
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
    }

//    public Contact(String contactName){
//        this.contactName = contactName;
//    }

    //getter methods for the declared variables
    public String getContactName(){return contactName;}
    public String getContactEmail(){
        return contactEmail;
    }
    public String getContactNumber(){return contactNumber; }

    public void setName(String name){
        this.contactName = name;
    }
    public void setEmail(String email){
       this.contactEmail = email;
    }
    public void setNumber(String number){
        this.contactNumber = number;
    }
}
