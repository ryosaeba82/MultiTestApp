package com.starware.mpicciau.multitestapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class RecordAudioActivity extends AppCompatActivity {

    private Button recordAudioButton = null;
    private Button stopRecordAudioButton = null;
    private Button playAudioButton = null;
    private MediaRecorder mediaRecorder = null;
    private String audioFilePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recordAudioButton = (Button) findViewById(R.id.buttonRecordAudioId);
        stopRecordAudioButton = (Button) findViewById(R.id.buttonStopRecordAudioId);
        playAudioButton = (Button) findViewById(R.id.buttonPlayAudioId);
        recordAudioButton.setEnabled(true);
        stopRecordAudioButton.setEnabled(false);
        playAudioButton.setEnabled(false);

        File file = new File(getFilesDir(),"audio.3gp");
        audioFilePath = file.getAbsolutePath();

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(audioFilePath);

        setContentView(R.layout.activity_record_audio);
    }

    public void registraAudio(View view)
    {
        try
        {
            mediaRecorder.prepare();
            mediaRecorder.start();
            recordAudioButton.setEnabled(false);
            stopRecordAudioButton.setEnabled(true);
            String text = getResources().getString(R.string.recordAudioText);
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void stopRecordAudio(View view)
    {
        try
        {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            stopRecordAudioButton.setEnabled(false);
            playAudioButton.setEnabled(true);
            String text = getResources().getString(R.string.stopRecordAudioText);
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void playAudio(View view)
    {
        try
        {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            playAudioButton.setEnabled(false);
            String text = getResources().getString(R.string.playAudioText);
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
