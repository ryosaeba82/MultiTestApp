package com.starware.mpicciau.multitestapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
    }

    public void createContact(View view){
        EditText editTextNome = (EditText) findViewById(R.id.editTextNome_id);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone_id);
        EditText editTextPhone2 = (EditText) findViewById(R.id.editTextSecondPhone_id);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail_id);
        StringBuffer messageBuffer = new StringBuffer("");

        if ( editTextNome.getText() == null || editTextNome.getText().length() == 0 )
        {
            String message = getResources().getString(R.string.noName);
            messageBuffer.append(message + "\n");
        }
        if ( editTextPhone.getText() == null || editTextPhone.getText().length() == 0 )
        {
            String message = getResources().getString(R.string.noPhone);
            messageBuffer.append(message + "\n");
        }
        if ( messageBuffer.toString().length() > 0 )
        {
            Toast.makeText(this,messageBuffer.toString(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent createContactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
            createContactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            List<ContentValues> data = new ArrayList<ContentValues>();
            createContactIntent.putExtra(ContactsContract.Intents.Insert.NAME,editTextNome.getText().toString());
            createContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE,editTextPhone.getText().toString());
            if ( editTextEmail.getText() != null && editTextEmail.getText().length() > 0 )
            {
				/*
				ContentValues emailContentValue = new ContentValues();
				emailContentValue.put(Data.MIMETYPE,Email.CONTENT_ITEM_TYPE);
				emailContentValue.put(Email.ADDRESS,editTextEmail.getText().toString());
				data.add(emailContentValue);
				*/
                createContactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL,editTextEmail.getText().toString());
            }
            if ( editTextPhone2.getText() != null || editTextPhone2.getText().length() > 0 )
            {
				/*
				ContentValues phone2ContentValue = new ContentValues();
				phone2ContentValue.put(Data.MIMETYPE,Phone.CONTENT_ITEM_TYPE);
				phone2ContentValue.put(Phone.TYPE,Phone.TYPE_OTHER);
				phone2ContentValue.put(Phone.NUMBER,editTextPhone2.getText().toString());
				data.add(phone2ContentValue);
				*/
                createContactIntent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE,editTextPhone2.getText().toString());
            }
            startActivity(createContactIntent);
        }
    }
}
