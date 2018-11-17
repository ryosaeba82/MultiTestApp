package com.starware.mpicciau.multitestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ContactsFragment extends Fragment {

    public ContactsFragment() {
        // Required empty public constructor
    }

    private void SearchContact_execute(View view){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contacts, container, false);

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
