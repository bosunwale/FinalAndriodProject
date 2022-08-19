package ca.johnoluwale.contactme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewContactActivity extends AppCompatActivity {
    public static final String EXTRA_EDIT_ID= "com.example.android.roomcontact.REPLY.Id";
    public static final String EXTRA_REPLY_NAME= "com.example.android.roomcontact.REPLY.Name";
    public static final String EXTRA_REPLY_EMAIL= "com.example.android.roomcontact.REPLY.Email";
    public static final String EXTRA_REPLY_NUMBER= "com.example.android.roomcontact.REPLY.Number";

    public static final String EXTRA_REPLY_TYPE= "com.example.android.roomcontact.REPLY.TYPE";
    public static final String EXTRA_REPLY_ISUPDATE= "com.example.android.roomcontact.REPLY.IsUpdate";
    public static final String EXTRA_REPLY_ISADD= "com.example.android.roomcontact.REPLY.IsAdd";
    public static final String EXTRA_REPLY_ISDELETE= "com.example.android.roomcontact.REPLY.IsDelete";

    private boolean isUpdate = false;

    private EditText contactEditTexName, contactEditTextEmail, contactEditTextNumber;
    private TextView displayTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactEditTexName = findViewById(R.id.contact_name);
        contactEditTextEmail = findViewById(R.id.contact_email);
        contactEditTextNumber = findViewById(R.id.contact_number);
        displayTxt = findViewById(R.id.displayEmailTxtView);
        /*
        Collecting contact details from ContactListAdapter and displaying
        them in NewActivityContact class
         */
        String name = getIntent().getStringExtra(ContactListAdapter.EXTRA_EDIT_NAME);
        String email = getIntent().getStringExtra(ContactListAdapter.EXTRA_EDIT_EMAIL);
        String number = getIntent().getStringExtra(ContactListAdapter.EXTRA_EDIT_NUMBER);
        if((name != null && !name.isEmpty())
                && (email != null && !email.isEmpty())
                && (number != null && !number.isEmpty())){
           isUpdate = true;
            contactEditTexName.setText(name);
            contactEditTextEmail.setText(email);
            contactEditTextNumber.setText(number);
        }
        final Button saveButton = findViewById(R.id.button_save);
        final Button delButton = findViewById(R.id.button_delete);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(contactEditTexName.getText()) && TextUtils.isEmpty(contactEditTextEmail.getText())
                    && TextUtils.isEmpty(contactEditTextNumber.getText())){
                    setResult(RESULT_CANCELED, intent);
                }else{
                    String contactName = contactEditTexName.getText().toString();
                    String contactEmail = contactEditTextEmail.getText().toString();
                    String contactNumber = contactEditTextNumber.getText().toString();
                    intent.putExtra(EXTRA_REPLY_NAME, contactName);
                    intent.putExtra(EXTRA_REPLY_EMAIL, contactEmail);
                    intent.putExtra(EXTRA_REPLY_NUMBER, contactNumber);
                    if(( isUpdate && contactName != null && !contactName.isEmpty())
                            && (contactEmail != null && !contactEmail.isEmpty())
                            && (contactNumber != null && !contactNumber.isEmpty())) {
                        intent.putExtra(EXTRA_REPLY_TYPE, EXTRA_REPLY_ISUPDATE);
                        int id = getIntent().getIntExtra(ContactListAdapter.EXTRA_EDIT_ID, -1);
                        intent.putExtra(EXTRA_EDIT_ID, id);
                        setResult(RESULT_OK, intent);
                    }
                    else{
                        intent.putExtra(EXTRA_REPLY_TYPE, EXTRA_REPLY_ISADD);
                        setResult(RESULT_OK, intent);
                    }
                }
                finish();
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra(ContactListAdapter.EXTRA_EDIT_ID, -1);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_REPLY_TYPE, EXTRA_REPLY_ISDELETE);
                intent.putExtra(EXTRA_EDIT_ID, id);
                setResult(RESULT_OK, intent);
                finish();;
            }
        });
    }
}