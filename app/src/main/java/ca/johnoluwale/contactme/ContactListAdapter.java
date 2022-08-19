package ca.johnoluwale.contactme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class that will display the list of contacts in the recycler view
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    //creating a member variable for the inflater
    private final LayoutInflater layoutInflater;
    //Cached copy of contacts
    private List<Contact> contacts;
    Contact current;
    String contactName;
    String contactEmail;
    String contactNumber;
    int contactId;
    Context context;

    public static final String EXTRA_EDIT_ID= "com.example.android.roomcontact.EDIT.Id";
    public static final String EXTRA_EDIT_NAME= "com.example.android.roomcontact.EDIT.Name";
    public static final String EXTRA_EDIT_EMAIL= "com.example.android.roomcontact.EDIT.Email";
    public static final String EXTRA_EDIT_NUMBER= "com.example.android.roomcontact.EDIT.Number";

    ContactListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        contactEditTexName.findViewById(R.id.contact_name);
//        contactEditTextEmail.findViewById(R.id.contact_email);
//        contactEditTextNumber.findViewById(R.id.contact_number);
        if (contacts != null){
            current = contacts.get(position);
            holder.contactItemView.setText(current.getContactName());
        }else{
            holder.contactItemView.setText("No Contact");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edit contact list
                current = contacts.get(position);
                contactId = current.id;
                contactName = current.getContactName();
                contactEmail = current.getContactEmail();
                contactNumber = current.getContactNumber();

                Intent intent = new Intent(v.getContext(), NewContactActivity.class);
                intent.putExtra(EXTRA_EDIT_ID, contactId);
                intent.putExtra(EXTRA_EDIT_NAME, contactName);
                intent.putExtra(EXTRA_EDIT_EMAIL, contactEmail);
                intent.putExtra(EXTRA_EDIT_NUMBER, contactNumber);
                ((Activity)context).startActivityForResult(intent, MainActivity.NEW_CONTACT_ACTIVITY_REQUEST_CODE);
                //v.getContext().startActivity(intent);
            }
        });
    }

    public void setContacts(List<Contact> listContacts){
        contacts = listContacts;
        notifyDataSetChanged();
    }
    /*
    getItemCount() is called many times, and when it is first called
    contacts has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        }else {
            return 0;
        }
    }
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);
        }
    }
    public Contact getContactAtPosition(int position){
        return contacts.get(position);
    }

    public Contact getContactByContactId(int target){
        for (Contact c : contacts){
            if (c.id == target){
                return c;
            }
        }
        return  null;
    }
}
