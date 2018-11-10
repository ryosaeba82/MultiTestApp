package com.starware.mpicciau.multitestapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntentMenuFragment extends Fragment {


    public IntentMenuFragment() {
        // Required empty public constructor
    }

    private void Geolocation_execute(View view){
        Uri locationUri = Uri.parse("geo:41.8957703,12.4803852?z=17");
        Intent implicitIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        startActivity(implicitIntent);
    }

    private void StartUrl_execute(View view){
        Uri webUri = Uri.parse("http://www.google.it");
        Intent implicitIntent = new Intent(Intent.ACTION_VIEW,webUri);
        startActivity(implicitIntent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_intent_menu, container, false);

        //Button Call management
        Button button_call = view.findViewById(R.id.button_call);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CallActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //Button Geo management
        Button button_geo = view.findViewById(R.id.button_geo);
        button_geo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Geolocation_execute(view);
            }
        });

        //Button Start Internet
        Button button_web = view.findViewById(R.id.button_web);
        button_web.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                StartUrl_execute(view);
            }
        });

        return view;
    }
}
