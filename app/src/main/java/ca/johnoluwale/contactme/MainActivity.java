package ca.johnoluwale.contactme;

//import android.arch.lifecycle.ViewModelProvider;
//import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.johnoluwale.contactme.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ContactViewModel contactViewModel;
    private ContactListAdapter contactListAdapter;
    private RecyclerView conRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

        conRecyclerView = findViewById(R.id.recyclerview);
        contactListAdapter = new ContactListAdapter(this);
        conRecyclerView.setAdapter(contactListAdapter);
        conRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        contactViewModel.getmAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactListAdapter.setContacts(contacts);
            }
        });
    }
    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //Enabling the save button to add and edit contact
        if(requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            if (data.getStringExtra(NewContactActivity.EXTRA_REPLY_TYPE).equals(NewContactActivity.EXTRA_REPLY_ISUPDATE)){
                Contact contact = contactListAdapter.getContactByContactId(data.getIntExtra(NewContactActivity.EXTRA_EDIT_ID, -1));
                if(contact != null) {
                    contact.setName(data.getStringExtra(NewContactActivity.EXTRA_REPLY_NAME));
                    contact.setEmail(data.getStringExtra(NewContactActivity.EXTRA_REPLY_EMAIL));
                    contact.setNumber(data.getStringExtra(NewContactActivity.EXTRA_REPLY_NUMBER));
                    contactViewModel.updateContact(contact);
                }
            }else if(data.getStringExtra(NewContactActivity.EXTRA_REPLY_TYPE ).equals(NewContactActivity.EXTRA_REPLY_ISADD)){
                Contact contact = new Contact(
                        data.getStringExtra(NewContactActivity.EXTRA_REPLY_NAME),
                        data.getStringExtra(NewContactActivity.EXTRA_REPLY_EMAIL),
                        data.getStringExtra(NewContactActivity.EXTRA_REPLY_NUMBER));
                contactViewModel.insert(contact);
            }else if (data.getStringExtra(NewContactActivity.EXTRA_REPLY_TYPE).equals(NewContactActivity.EXTRA_REPLY_ISDELETE)){
                Contact contact = contactListAdapter.getContactByContactId(data.getIntExtra(NewContactActivity.EXTRA_EDIT_ID, -1));
                if (contact != null){
                    contactViewModel.deleteContact(contact);
                }
            }

        }else{
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}