package com.starware.mpicciau.multitestapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;


public class ContactsFragment extends Fragment {

    ListView contactsListView;
    EditText contactText;

    public ContactsFragment() {
        // Required empty public constructor
    }

    private void ShowContact(AdapterView<?> arg0, View arg1, int position, long arg3){

    }

    //Ricerca dei contatti utilizzando come stringa di ricerca il contenuto della edittext
    private void SearchContact_execute(View view){

        if(contactText.getText().toString().length() == 0)
        {
            Toast.makeText(getActivity(),getString(R.string.alert_contactsearch_empty),Toast.LENGTH_LONG).show();
            return;
        }

        int permissionGranted = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS);
        if(permissionGranted == PackageManager.PERMISSION_DENIED){
            Toast.makeText(getActivity(),getString(R.string.alert_no_permission),Toast.LENGTH_LONG).show();
            return;
        }

        //ricerca dei contatti con query SQL
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        String contactId = ContactsContract.Contacts._ID;
        String contactLookupKey = ContactsContract.Contacts.LOOKUP_KEY;
        String contactName = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME;
        String [] projection = {contactId,contactLookupKey,contactName};
        String sqlWhere = contactName + " LIKE ?";
        String [] sqlWhereConditions = {"%" + contactText.getText().toString() + "%"};
        Cursor cursor = contentResolver.query(contentUri,projection,sqlWhere,sqlWhereConditions,null);

        if ( cursor.getCount() > 0 )
        {
            ArrayList<String> data = new ArrayList<String>();
            HashMap<String, Long> nameToId = new HashMap<String, Long>();
            HashMap<String, String> nameToLookupKey = new HashMap<String, String>();
            while ( cursor.moveToNext() )
            {
                String name = cursor.getString(cursor.getColumnIndex(contactName));
                Long id = cursor.getLong(cursor.getColumnIndex(contactId));
                String lookupKey = cursor.getString(cursor.getColumnIndex(contactLookupKey));
                data.add(name);
                nameToId.put(name,id);
                nameToLookupKey.put(name,lookupKey);
            }

            //visualizzazione contatti
            int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
            contactsListView.setAdapter(new ArrayAdapter<String>(getActivity(),layout,data));
            //impostazione evento click sul contatto
            contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3){
                    ShowContact(arg0, arg1, position, arg3);
                }
            });
        }
        else
        {
            Toast.makeText(getActivity(), getString(R.string.alert_no_contacts), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contacts, container, false);

        //ListView con elenco dei contatti
        contactsListView = view.findViewById(R.id.contactListView);

        //EditText per ricerca dei contatti
        contactText = view.findViewById(R.id.contactSearchEdit);

        //Button Search contact
        Button button_search = view.findViewById(R.id.button_searchContact);
        button_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SearchContact_execute(view);
            }
        });
        return view;
    }

}
