package com.tecpro.mcapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    boolean battle,concept;
    int timeImprovisation, timeParticipants,timeConcept;
    String recordName;
    View dialog_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                    99);
            return;
        } else {
            ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.VISIBLE);
            ((myCustomTextView) findViewById(R.id.battle)).setVisibility(View.VISIBLE);
            ((myCustomTextView) findViewById(R.id.records)).setVisibility(View.VISIBLE);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.VISIBLE);
                    ((myCustomTextView) findViewById(R.id.battle)).setVisibility(View.VISIBLE);
                    ((myCustomTextView) findViewById(R.id.records)).setVisibility(View.VISIBLE);

                } else {
                    ((myCustomTextView) findViewById(R.id.individual)).setText(getResources().getString(R.string.acceptPermissions));
                    ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.VISIBLE);
                }
                return;
            }
        }
    }

    private void ini(){
        timeImprovisation = 120;
        timeParticipants = 15;
        timeConcept  = 15;
        battle = false;
        concept = false;
        recordName = "";
        ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.battle)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.records)).setVisibility(View.GONE);
    }


    private void firstPress(){
        ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.battle)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.records)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.free)).setVisibility(View.VISIBLE);
        ((myCustomTextView) findViewById(R.id.concept)).setVisibility(View.VISIBLE);
    }

    private void secondPress(){
        ((myCustomTextView) findViewById(R.id.free)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.concept)).setVisibility(View.GONE);
        ((myCustomTextView) findViewById(R.id.next)).setVisibility(View.VISIBLE);
        ((CheckBoxPlus) findViewById(R.id.checkBoxRecord)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textImprovisation)).setVisibility(View.VISIBLE);
        SeekBar seekBarImprovisation =  ((SeekBar) findViewById(R.id.seekBarImprovisation));
        seekBarImprovisation.setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textTimeImprovisation)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textSpace2)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textSpace4)).setVisibility(View.VISIBLE);
        if (battle){
            ((TextView) findViewById(R.id.textParticipants)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.textTimeParticipants)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.textSpace3)).setVisibility(View.VISIBLE);
            SeekBar seekBarParticipants = ((SeekBar) findViewById(R.id.seekBarParticipants));
            seekBarParticipants.setVisibility(View.VISIBLE);
            seekBarParticipants.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                TextView textView = (TextView) findViewById(R.id.textTimeParticipants);
                @Override
                public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                    int MIN = 5;
                    if (progresValue >= MIN) {
                        timeParticipants = progresValue;
                    } else {
                        timeParticipants = MIN;
                    }
                    textView.setText(String.valueOf(timeParticipants) + getResources().getString(R.string.seconds));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // Display the value in textview
                    textView.setText(String.valueOf(timeParticipants) + getResources().getString(R.string.seconds));

                }
            });

        }
        seekBarImprovisation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView textView = (TextView) findViewById(R.id.textTimeImprovisation);
            SeekBar seekBarConcept = ((SeekBar) findViewById(R.id.seekBarConcept));
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                int MIN = 10;
                if (!(progresValue % 2 == 0)) {
                    timeImprovisation = ++progresValue;
                }
                if (progresValue >= MIN ) {
                    timeImprovisation = progresValue;
                } else {
                    timeImprovisation = MIN;
                }
                textView.setText(String.valueOf(timeImprovisation) + getResources().getString(R.string.seconds));
                if (seekBarConcept.getVisibility() == View.VISIBLE && timeImprovisation < timeConcept){
                    TextView textView = (TextView) findViewById(R.id.textTimeConcept);
                    timeConcept = timeImprovisation;
                    textView.setText(String.valueOf(timeConcept) + getResources().getString(R.string.seconds));
                    seekBarConcept.setProgress(timeConcept);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                textView.setText(String.valueOf(timeImprovisation) + getResources().getString(R.string.seconds));

            }
        });


    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.records:
                Intent intent = new Intent(this, RecordsActivity.class);
                startActivity(intent);
                break;
            case R.id.individual:
                firstPress();
                break;
            case R.id.battle:
                firstPress();
                battle = true;
                break;
            case R.id.free:
                secondPress();
                break;
            case R.id.concept:
                concept = true;
                secondPress();
                SeekBar seekbar = ((SeekBar) findViewById(R.id.seekBarConcept));
                seekbar.setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.textConcept)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.textTimeConcept)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.textSpace)).setVisibility(View.VISIBLE);
                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    TextView textView = (TextView) findViewById(R.id.textTimeConcept);

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                        int MIN = 5;
                        if (progresValue >= MIN) {
                            timeConcept = progresValue;
                        } else {
                            timeConcept = MIN;
                        }
                        textView.setText(String.valueOf(timeConcept) + getResources().getString(R.string.seconds));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // Display the value in textview
                        textView.setText(String.valueOf(timeConcept) + getResources().getString(R.string.seconds));
                    }
                });
                break;
            case R.id.next:
                Intent i = new Intent(this, SongsActivity.class);
                if (!battle)
                    timeParticipants = 0;
                i.putExtra("timeParticipants", timeParticipants);
                i.putExtra("timeImprovisation", timeImprovisation);
                if (!concept)
                    timeConcept = 0;
                i.putExtra("timeConcept", timeConcept);
                if (((CheckBoxPlus) findViewById(R.id.checkBoxRecord)).isChecked())
                    recordName = "MCAppRecord"+ UUID.randomUUID().toString();
                i.putExtra("recordName", recordName);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (((myCustomTextView) findViewById(R.id.individual)).getVisibility() == View.VISIBLE ){
            super.onBackPressed();
        }
        if (((myCustomTextView) findViewById(R.id.free)).getVisibility() == View.VISIBLE ){
            ((myCustomTextView) findViewById(R.id.free)).setVisibility(View.GONE);
            ((myCustomTextView) findViewById(R.id.concept)).setVisibility(View.GONE);
            ((myCustomTextView) findViewById(R.id.individual)).setVisibility(View.VISIBLE);
            ((myCustomTextView) findViewById(R.id.battle)).setVisibility(View.VISIBLE);
            ((myCustomTextView) findViewById(R.id.records)).setVisibility(View.VISIBLE);
        }
        if (((myCustomTextView) findViewById(R.id.next)).getVisibility() == View.VISIBLE ){
            ((myCustomTextView) findViewById(R.id.next)).setVisibility(View.GONE);
            ((CheckBoxPlus) findViewById(R.id.checkBoxRecord)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textImprovisation)).setVisibility(View.GONE);
            SeekBar seekBarImprovisation =  ((SeekBar) findViewById(R.id.seekBarImprovisation));
            seekBarImprovisation.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textTimeImprovisation)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textSpace2)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textSpace4)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textParticipants)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textTimeParticipants)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textSpace3)).setVisibility(View.GONE);
            SeekBar seekBarParticipants = ((SeekBar) findViewById(R.id.seekBarParticipants));
            seekBarParticipants.setVisibility(View.GONE);
            SeekBar seekbar = ((SeekBar) findViewById(R.id.seekBarConcept));
            seekbar.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textConcept)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textTimeConcept)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textSpace)).setVisibility(View.GONE);
            firstPress();
        }
    }

}
