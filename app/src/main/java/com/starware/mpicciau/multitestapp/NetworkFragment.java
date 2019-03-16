package com.starware.mpicciau.multitestapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {

    private static int MAX_CHAR_TO_READ = 10*1024;
    private static String WEB_URL= "http://www.google.it";

    private View fragmentView = null;

    public NetworkFragment() {
        // Required empty public constructor
    }

    private void ExecuteOpenUrl(View view)
    {
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            NetworkInfo networkInfo = null;
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if ( networkInfo != null && networkInfo.isConnected() )
            {
                String result = "";
                try
                {
                    URL url = new URL(WEB_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    Reader reader = new InputStreamReader(is);
                    char[] buffer = new char[MAX_CHAR_TO_READ];
                    reader.read(buffer);
                    result = new String(buffer);
                    TextView textView = (TextView) fragmentView.findViewById(R.id.textViewId);
                    textView.setText(result);
                }
                catch ( Exception ex )
                {
                    Toast.makeText(getActivity(),"Eccezione: " + ex.getMessage() + " " +  ex.toString(),Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
            else
            {
                String text = getResources().getString(R.string.noNetwork);
                Toast.makeText(getActivity(),text,Toast.LENGTH_LONG).show();
            }
        }
        catch ( Exception ex )
        {
            Toast.makeText(getActivity(),"Eccezione: " + ex.getMessage() + " " + ex.toString(),Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_network, container, false);

        //gestion OnClick bottone OpenURL
        Button button_openurl = fragmentView.findViewById(R.id.buttonOpenUrlId);
        button_openurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecuteOpenUrl(view);
            }
        });

        return(fragmentView);
    }

}
