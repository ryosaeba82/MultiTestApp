package com.starware.mpicciau.multitestapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class CameraFragment extends Fragment {

    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_VIDEO = 2;
    private ImageView imageView;
    private View fragment_view;

    public CameraFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment_view = inflater.inflate(R.layout.fragment_camera, container, false);

        //Button Photo management
        Button button_photo = fragment_view.findViewById(R.id.buttonPhotoId);
        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoExecute(view);
            }
        });

        //Button Video management
        Button button_video = fragment_view.findViewById(R.id.buttonVideoId);
        button_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoExecute(view);
            }
        });

        return(fragment_view);
    }

    private void VideoExecute(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,REQUEST_VIDEO);
    }

    private void PhotoExecute(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_PHOTO);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageView = (ImageView) fragment_view.findViewById(R.id.imageViewId);
            imageView.setImageBitmap(bitmap);
        }
        else if ( requestCode == REQUEST_VIDEO && resultCode == RESULT_OK )
        {
            Uri videoUri = data.getData();
            Toast.makeText(getActivity(),"Video url: " + videoUri.getPath(),Toast.LENGTH_LONG).show();
        }
    }
}
