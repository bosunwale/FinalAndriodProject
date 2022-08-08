package ca.johnoluwale.contactme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    ContactListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ContactViewHolder holder, int position) {
        if (contacts != null){
            Contact current = contacts.get(position);
            holder.contactItemView.setText(current.getContactName());
        }else{
            holder.contactItemView.setText("No Contact");
        }
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
    public class ContactViewHolder extends RecyclerView.ViewHolder{
        private final TextView contactItemView;
        private ContactViewHolder(View itemView){
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);
        }
    }
}
