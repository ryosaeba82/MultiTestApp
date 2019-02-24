package com.starware.mpicciau.multitestapp;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import static android.support.v4.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultimediaFragment extends Fragment {

    AudioManager audioManager;
    RadioGroup radioGroup;
    RadioButton radioButtonSilent;
    RadioButton radioButtonNormal;
    TextView textView;

    public MultimediaFragment() {
        // Required empty public constructor
    }

    void ImpostaSuono(View view) {

        String text = "";

        if (radioGroup.getCheckedRadioButtonId() == radioButtonSilent.getId()) {
            //modo silenzioso
            audioManager.setRingerMode(audioManager.RINGER_MODE_SILENT);
            text =  getResources().getString(R.string.ringerModeSilentText);

        } else if (radioGroup.getCheckedRadioButtonId() == radioButtonNormal.getId()) {
            //modo normale
            audioManager.setRingerMode(audioManager.RINGER_MODE_NORMAL);
            text =  getResources().getString(R.string.ringerModeNormalText);
        } else {
            //vibrazione
            audioManager.setRingerMode(audioManager.RINGER_MODE_VIBRATE);
            text =  getResources().getString(R.string.ringerModeVibrateText);
        }
        textView.setText(text);
        Toast.makeText(getActivity().getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    void IncreaseVolume(View view) {
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
        String text = getResources().getString(R.string.volumeUpText);
        Toast.makeText(getActivity().getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    void DecreaseVolume(View view) {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
        String text = getResources().getString(R.string.volumeDownText);
        Toast.makeText(getActivity().getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    void StartRecorder(View view){
        Intent intent = new Intent(getActivity().getApplicationContext(),RecordAudioActivity.class);
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multimedia, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        radioGroup = view.findViewById(R.id.radioGroupId);
        radioButtonSilent = view.findViewById(R.id.radioButtonSilentId);
        radioButtonNormal = view.findViewById(R.id.radioButtonNormalId);
        textView = view.findViewById(R.id.textViewRingModeId);

        //Button Call management
        Button button_call = view.findViewById(R.id.buttonChooseModeId);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImpostaSuono(view);
            }
        });

        Button button_increase = view.findViewById(R.id.buttonIncreaseVolumeId);
        button_increase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                IncreaseVolume(view);
            }
        });

        Button button_decrease = view.findViewById(R.id.buttonDecreaseVolumeId);
        button_decrease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DecreaseVolume(view);
            }
        });

        Button button_recorder = view.findViewById(R.id.buttonApriRecordAudioActivityId);
        button_recorder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                StartRecorder(view);
            }
        });



        return(view);
    }

}
