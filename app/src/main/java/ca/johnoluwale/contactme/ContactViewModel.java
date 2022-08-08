package ca.johnoluwale.contactme;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The ViewModel will hold the apps UI data to make sure the
 * data survives any configuration changes
 */
public class ContactViewModel extends AndroidViewModel {
    //private member variable to hold reference to repository
    private ContactRepository contactRepository;
    //private member variable to cache list of contacts
    private LiveData<List<Contact>> mAllContacts;
    /*
    Constructor to get the reference to the repository and retrieves
    the list of all contacts from the ContactRepository
     */
    public ContactViewModel(Application application){
        super(application);
        contactRepository = new ContactRepository(application);
        mAllContacts = contactRepository.getnAllContacts();
    }
    /*
    Adding a getter method to retrieve all the contacts
    Creating a wrapper insert() method() to call the Repository insert() method
    Note: the getter and wrapper insert method is completely hidden from the UI
     */
    LiveData<List<Contact>> getmAllContacts(){
        return mAllContacts;
    }
    public void insert(Contact contact){
        contactRepository.insert(contact);
    }
}
