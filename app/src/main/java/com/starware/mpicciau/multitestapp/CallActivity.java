package com.starware.mpicciau.multitestapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    private Button buttonCall;
    private EditText editPhoneNumber;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        buttonCall = findViewById(R.id.button_MakeCall);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
    }

    public void onCallClick(View view)
    {
        String PhoneNumber = editPhoneNumber.getText().toString();
        if(PhoneNumber.isEmpty())
            Toast.makeText(this, R.string.empty_phone_nb, Toast.LENGTH_SHORT).show();
        else {
            Uri phoneUri = Uri.parse("tel:"+PhoneNumber);
            Intent implicitIntent = new Intent(Intent.ACTION_DIAL,phoneUri);
            startActivity(implicitIntent);
        }
    }

    public void onContactClick(View view){
        Uri uri = Uri.parse("content://contacts");
        Intent intent = new Intent(Intent.ACTION_PICK,uri);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();
                String[] selectFromClause = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver().query(uri,selectFromClause,null,null,null);
                cursor.moveToFirst();
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                editPhoneNumber.setText(phoneNumber);
            }
        }
    }
}
