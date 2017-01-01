package com.tecpro.mcapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjaimez on 25/5/16.
 */
public class RecordsActivity extends AppCompatActivity {

    List myList;
    int selected = -1;
    MusicAdapter m;
    MediaPlayer mp;
    private ShareActionProvider mShareActionProvider;
    MenuItem item, item2;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        ((myCustomTextView) findViewById(R.id.play)).setVisibility(View.GONE);
        lv = (ListView) findViewById(R.id.songs_list);
        loadAudio();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_share, menu);

        // Locate MenuItem with ShareActionProvider
        item = menu.findItem(R.id.share);
        item2 = menu.findItem(R.id.deleteMenu);
        // Fetch and store ShareActionProvider
        item.setVisible(false);
        item2.setVisible(false);
        // Return true to display menu
        return true;
    }


    public void onCick(View v){
        if (selected > -1){
            Bundle bundle = getIntent().getExtras();
            Intent i = new Intent(this, Play.class);
            i.putExtra("timeParticipants", bundle.getInt("timeParticipants"));
            i.putExtra("timeImprovisation", bundle.getInt("timeImprovisation"));
            i.putExtra("recordName", bundle.getString("recordName"));
            i.putExtra("timeConcept", bundle.getInt("timeConcept"));
            String[] s = (String[]) m.getItem(selected);
            i.putExtra("data",s[3]);
            startActivity(i);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (selected > -1) {
            switch (item.getItemId()) {
                case R.id.share:
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("audio/*");
                    String[] s = (String[]) m.getItem(selected);
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(s[3]));
                    startActivity(Intent.createChooser(share, "Share Sound File"));
                    return true;
                case R.id.deleteMenu:
                    if (mp != null && mp.isPlaying())
                        mp.stop();

                    String[] s2 = (String[]) m.getItem(selected);
                    Uri rootUri = MediaStore.Audio.Media.getContentUriForPath( s2[3] );
                    this.getContentResolver().delete( rootUri, MediaStore.MediaColumns.DATA + "=?", new String[]{ s2[3] } );

                    Intent intent = new Intent(this, RecordsActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return true;
            }
        }
        return true;

    }




    private void loadAudio(){
        //Some audio may be explicitly marked as not being music
        String selection = MediaStore.Audio.Media.TITLE+" LIKE '%MCAppRecord%'";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
        };



        Cursor cursor = this.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);

        myList = new ArrayList<String[]>();
        while(cursor.moveToNext()) {
            String[] s = { cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4) , cursor.getString(5)} ;
            myList.add(s);
        }


        m = new MusicAdapter(this,myList);
        lv.setAdapter(m);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                if (selected > -1) {
                    View view2 = getViewByPosition(selected, lv);
                    TextView textView = (TextView) view2.findViewById(R.id.textName);
                    textView.setTextColor(getResources().getColor(R.color.black));
                }

                selected = position;
                TextView textView = (TextView) view.findViewById(R.id.textName);
                textView.setTextColor(getResources().getColor(R.color.colorAccent));


                try {
                    String[] s = (String[]) m.getItem(selected);
                    if (mp != null && mp.isPlaying())
                        mp.release();
                    mp = new MediaPlayer();
                    mp.setDataSource(s[3]);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                item.setVisible(true);
                item2.setVisible(true);
            }
        });
    }



    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    public void onBackPressed() {
        if (mp != null && mp.isPlaying())
            mp.stop();
        super.onBackPressed();
    }
}
