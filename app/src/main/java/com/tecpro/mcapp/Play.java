package com.tecpro.mcapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Play extends AppCompatActivity {
    MediaPlayer mp;
    TextView textViewToday;
    MediaRecorder mRecorder;
    ImageView imageView;
    Bundle bundle;
    Img img;
    File audiofile;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        bundle = getIntent().getExtras();
        textViewToday = (TextView) findViewById(R.id.textViewToday);
        imageView = (ImageView) findViewById(R.id.imageView);
        pb = (ProgressBar) findViewById(R.id.progressBarToday);

        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewToday.setText(getResources().getString(R.string.nextMC)+String.valueOf(millisUntilFinished / 1000));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                startPlaying();
                if (!bundle.getString("recordName").isEmpty()){
                    startRecording(false);
                }
                if (bundle.getInt("timeParticipants") > 0 ){

                }
            }
        }.start();


    }



    private void startPlaying() {
        mp = new MediaPlayer();
        try {
            if (bundle.getString("data").equals(String.valueOf(R.raw.base1)) || bundle.getString("data").equals(String.valueOf(R.raw.base2)) ){
                mp = MediaPlayer.create(this, Integer.valueOf(bundle.getString("data")));

            } else {
                mp.setDataSource(bundle.getString("data"));
                mp.prepare();
                mp.start();
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        img = new Img();

        if (bundle.getInt("timeConcept") != 0)
            imageView.setImageResource(img.nextImage());

        Animation an = new RotateAnimation(90.0f, 0.0f, 250f, 273f);
        an.setFillAfter(true);
        pb.startAnimation(an);
        pb.setMax(bundle.getInt("timeImprovisation"));


        new CountDownTimer(bundle.getInt("timeImprovisation") * 1000, 1000) {
            int tik  = 0;
            int tik2  = 0;
            public void onTick(long millisUntilFinished) {
                tik2++;
                pb.setProgress(tik2);
                textViewToday.setText(String.valueOf((int) millisUntilFinished / 1000));

                if (bundle.getInt("timeConcept") != 0 && tik == bundle.getInt("timeConcept")) {
                    tik = 0;
                    imageView.setImageResource(img.nextImage());
                }
                tik++;
            }

            public void onFinish() {
                mp.release();
                stopRecording();
                if (bundle.getInt("timeParticipants") > 0 ){
                    opponent();
                } else {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }.start();
    }

    //true si se graba al oponente
    private void startRecording(boolean b) {

        try {
       mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            File sampleDir = Environment.getExternalStorageDirectory();
            audiofile = File.createTempFile(bundle.getString("recordName"), ".3gp", sampleDir);
            mRecorder.setOutputFile(audiofile.getAbsolutePath());
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }


    }

    private void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
            addRecordingToMediaLibrary();
        }
    }

    private void opponent(){

        imageView.setImageResource(0); //SETEAR IMAGEN VACIA
        pb.setProgress(0);
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewToday.setText(getResources().getString(R.string.nextMC)+String.valueOf(millisUntilFinished / 1000));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                startPlayingOpponent();
                if (!bundle.getString("recordName").isEmpty()){
                    startRecording(true);
                }
            }
        }.start();




    }

    public void startPlayingOpponent(){
        mp = new MediaPlayer();
        try {
            if (bundle.getString("data").equals(String.valueOf(R.raw.base1)) || bundle.getString("data").equals(String.valueOf(R.raw.base2)) ){
                mp = MediaPlayer.create(this, Integer.valueOf(bundle.getString("data")));

            } else {
                mp.setDataSource(bundle.getString("data"));
                mp.prepare();
                mp.start();
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        img = new Img();

        if (bundle.getInt("timeConcept") != 0)
            imageView.setImageResource(img.nextImage());

        Animation an = new RotateAnimation(90.0f, 0.0f, 250f, 273f);
        an.setFillAfter(true);
        pb.startAnimation(an);
        pb.setMax(bundle.getInt("timeImprovisation"));

        new CountDownTimer(bundle.getInt("timeImprovisation") * 1000, 1000) {
            int tik  = 0;
            int tik2  = 0;
            public void onTick(long millisUntilFinished) {
                textViewToday.setText(String.valueOf(millisUntilFinished / 1000));
                tik2++;
                pb.setProgress(tik2);
                textViewToday.setText(String.valueOf((int) millisUntilFinished / 1000));

                if (bundle.getInt("timeConcept") != 0 && tik == bundle.getInt("timeConcept")) {
                    tik = 0;
                    imageView.setImageResource(img.nextImage());
                }
                tik++;
            }

            public void onFinish() {
                mp.release();
                stopRecording();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    protected void addRecordingToMediaLibrary() {
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
        ContentResolver contentResolver = getContentResolver();

        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
    }

    @Override
    public void onBackPressed() {

    }

}
