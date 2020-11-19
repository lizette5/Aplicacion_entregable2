package com.example.aplicacion.ui.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplicacion.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicListF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicListF extends DialogFragment {
    RecyclerView listademusicas;
    Activity actividad;
    List<MusicElemnt> musicas;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MusicListF() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return CrearDialogMusic();
    }

    private AlertDialog CrearDialogMusic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_music_list,null);
        builder.setView(v);

        listademusicas = v.findViewById(R.id.recyclerpop3);
        musicas = new ArrayList<>();
        musicas =getAllAudioFromDevice(this.getActivity());
        listademusicas.setHasFixedSize(true);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getActivity());
        listademusicas.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter (musicas, this.getActivity());
        listademusicas.setAdapter(mAdapter);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity)context;

        }else{
            throw new RuntimeException(context.toString()+"make onFragmentInteraccftionLisas");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment musicListF.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicListF newInstance(String param1, String param2) {
        MusicListF fragment = new MusicListF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_list, container, false);
    }

    public List<MusicElemnt> getAllAudioFromDevice(final Context context) {
        int i=0;
        final List<MusicElemnt> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.ArtistColumns.ARTIST,MediaStore.Audio.AudioColumns.DATA};
        Cursor c = context.getContentResolver().query(uri,
                projection,
                null,
                null,
                null);

        if (c != null) {
            while (c.moveToNext()) {
                MusicElemnt audioModel = new MusicElemnt();

                String name = c.getString(0);
                String artist = c.getString(1);
                String path = c.getString(2);
                audioModel.setName(name);
                audioModel.setArtist(artist);
                audioModel.setPath(path);
                tempAudioList.add(audioModel);
                Log.e("Name :" + name, " pATHHH :" + path);
                //Log.e("Path :" + path, " Artist :" + artist);

            }
            c.close();
        }

        return tempAudioList;
    }

}