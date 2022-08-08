package ca.johnoluwale.contactme;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This is the class that ContactRoomDatabase access because
 * it has multiple data sources. The repository class will handle
 * all the data operations and provide a clean API to the rest of
 * the app for the app data.
 * Furthermore, it manages query threads and allows for usage of
 * multiple threads
 */
public class ContactRepository {
    //adding member variables from the ContactDao and
    // Contact class
    private ContactDao nContacDao;
    private LiveData<List<Contact>> nAllContacts;

    /*
    Constructor that handles the database and initializes the member
    variables
     */
    ContactRepository(Application application){
        ContactRoomDatabase contactRoomDatabase = ContactRoomDatabase.getDatabase(application);
        nContacDao = contactRoomDatabase.contactDao();
        nAllContacts = nContacDao.getAllContact();
    }

    /*
    Adding a wrapper method that returns the cached contacts as
    LiveData
     */
    LiveData<List<Contact>> getnAllContacts(){
        return  nAllContacts;
    }

    /*
    Adding a wrapper for insert() method and using AsyncTask to
    call method to avoid crashing of application
     */
    public void insert(Contact contact){
        new insertAsyncTask(nContacDao).execute(contact);
    }
    /**
     * Creating the insertAsyncTask as an innerclass
     */
    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void>{
        private ContactDao mAsyncTaskDao;
        insertAsyncTask(ContactDao contactDao){
            mAsyncTaskDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            mAsyncTaskDao.insert(contacts[0]);
            return null;
        }
    }
}
