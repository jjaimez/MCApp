package com.tecpro.mcapp;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongsActivity extends AppCompatActivity {
    List myList;
    int selected = -1;
    MusicAdapter m;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        lv = (ListView) findViewById(R.id.songs_list);
        loadAudio();
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

    private void loadAudio(){
        //Some audio may be explicitly marked as not being music
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

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


        String[] s = {String.valueOf(R.raw.base1), "base1","base1", String.valueOf(R.raw.base1), "base1" , ""} ;
        myList.add(s);
        s = new String[]{String.valueOf(R.raw.base2), "base2", "base2", String.valueOf(R.raw.base2), "base2", ""};
        myList.add(s);



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
}
