package com.tecpro.mcapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by jjaimez on 16/12/15.
 */
public class MusicAdapter extends BaseAdapter {
    private LayoutInflater inflador; // Crea Layouts a partir del XML
    private TextView name;
    List<String[]> myList;

    public MusicAdapter(Context context, List myList) {
        inflador =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.myList = myList;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int posicion, View vistaReciclada,ViewGroup padre) {
        String[] song = myList.get(posicion);
        if (vistaReciclada == null) {
            vistaReciclada= inflador.inflate(R.layout.audio_item, null);
        }
        setReferences(vistaReciclada);
        name.setText(song[4]);
        return vistaReciclada;
    }

    private void setReferences(View vistaReciclada){
        name = (TextView) vistaReciclada.findViewById(R.id.textName);
    }

}
