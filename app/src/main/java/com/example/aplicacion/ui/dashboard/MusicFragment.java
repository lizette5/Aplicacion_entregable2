package com.example.aplicacion.ui.dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion.MainActivity2;
import com.example.aplicacion.R;

import java.io.IOException;
import java.util.*;

import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ListView;

public class MusicFragment extends Fragment {

    Context context = getActivity();
    //---------------------------------------------------------------------------------------------------------//
    private Dialog myDialog;
    private ImageView btnlistmusicpopup;
    private RecyclerView reciclerpopup;
    //---------------------------------------------------------------------------------------------------------//


    //---------------------------------------------------------------------------------------------------------//
    //VARIABLES PARA EL EL LISTADO//
    private TextView nameLabel;
    private MusicViewModel musicViewModel;
    private RecyclerView recyclerViewMusic;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;;
    boolean tamaño = true;
    List<MusicElemnt>musicas;
    MusicListF gialogolista = new MusicListF();

    //---------------------------------------------------------------------------------------------------------//


    //---------------------------------------------------------------------------------------------------------//
    //VARIABLES PARA EL REPRODUCTOR//
    public String Rmusica="";
    ImageView play, pause, btnnext, btnback;
    MediaPlayer mp;
    public MediaPlayer mediaPlayer;
    boolean flajButtom = true;
    //---------------------------------------------------------------------------------------------------------//
    int repetir =2, posicion =4;
    public List <MediaPlayer>vectortmp ;
    public ContentValues newSongDetails = new ContentValues();
    public String tmpValue="";

    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vectortmp = new ArrayList<MediaPlayer>();
        myDialog = new Dialog(getActivity());
        /*vectorsito[0] = MediaPlayer.create(getActivity(),R.raw.g);
        vectorsito[1] =MediaPlayer.create(getActivity(),R.raw.a);
        vectorsito[2] =MediaPlayer.create(getActivity(),R.raw.b);
        vectorsito[3] =MediaPlayer.create(getActivity(),R.raw.c);*/
        //---------------------------------------------------------------------------------------------------------//
        //PRUEBAS//
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);
        //---------------------------------------------------------------------------------------------------------//


        //---------------------------------------------------------------------------------------------------------//
        //ASIGNACIONES PARA CARDSLIST DE LAS MUSICAS//
        View root = inflater.inflate(R.layout.fragment_music, container, false);//ultraimportante
        recyclerViewMusic = root.findViewById(R.id.listMusic);
        //reciclerpopup = root.findViewById(R.id.recyclerpop2);
        nameLabel = root.findViewById(R.id.nameLabel);
        setRecicler();

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rmusica = musicas.get(recyclerViewMusic.getChildAdapterPosition(view)).getName();
                Toast.makeText(getContext(),"seleccion: "+ musicas.get(recyclerViewMusic.getChildAdapterPosition(view)).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        //---------------------------------------------------------------------------------------------------------//


        //---------------------------------------------------------------------------------------------------------//
        //CODIGO PARA EL REPRODUCTOR//
        play = root.findViewById(R.id.btnplay);
        btnnext = root.findViewById(R.id.btnnext);
        btnback = root.findViewById(R.id.btnback);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        //---------------------------------------------------------------------------------------------------------//




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepararMusica();
                if(mediaPlayer.isPlaying()){
                    try {
                        mediaPlayer.pause();
                        play.setBackgroundResource(R.drawable.tmp1);
                    }catch (Exception e){}
                }else {
                    try {
                        mediaPlayer.start();
                        play.setBackgroundResource(R.drawable.tmp2);
                    }catch (Exception e){}
                }
/*
                //if(vectortmp.get(posicion).isPla  ying()){//el audio se está reproducioendo o no
              if(vectorsito[posicion].isPlaying()){//el audio se está reproducioendo o no

                    //vectortmp.get(posicion).pause();
                    vectorsito[posicion].pause();
                    play.setBackgroundResource(R.drawable.tmp1);
                    Toast.makeText(getContext(), "se pausa en: ", Toast.LENGTH_SHORT).show();
                }else{
                    //vectortmp.get(posicion).start();//arranca la pista
                    try {
                        vectorsito[posicion].prepare();
                        vectorsito[posicion].start();//arranca la pista
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    play.setBackgroundResource(R.drawable.tmp2);
                    Toast.makeText(getContext(), "se reproduce en: ", Toast.LENGTH_SHORT).show();

                }
*/
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicion++;
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    flajButtom=true;
                    prepararMusica();
                    mediaPlayer.start();
                }else {
                    flajButtom=true;
                    prepararMusica();
                    mediaPlayer.start();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posicion--;
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    flajButtom=true;
                    prepararMusica();
                    mediaPlayer.start();
                }else {
                    flajButtom=true;
                    prepararMusica();
                    mediaPlayer.start();
                }
            }
        });
        btnlistmusicpopup = root.findViewById(R.id.listmusicpopup);
        //myDialog.setContentView(R.layout.listmusicpopup);
        myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnlistmusicpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gialogolista.show(getActivity().getSupportFragmentManager(),"dialogos de musica");

            }
        });



        return root;
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
    public void setRecicler(){
        //////
        musicas = new ArrayList<>();
        musicas =getAllAudioFromDevice(this.getActivity());
        recyclerViewMusic.setHasFixedSize(tamaño);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewMusic.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter (musicas, this.getActivity());
        recyclerViewMusic.setAdapter(mAdapter);

    }
    /*public void setRecler2(){
        reciclerpopup.setHasFixedSize(tamaño);
        reciclerpopup.setLayoutManager(layoutManager);////
        reciclerpopup.setAdapter(mAdapter);
    }*/

    public void prepararMusica(){
        if(flajButtom){
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(musicas.get(posicion).getPath());
            nameLabel.setText(musicas.get(posicion).getName());
            mediaPlayer.prepare();
            flajButtom = false;
        }catch (Exception e){}}
    }
    public void showPopUp(View view){
        btnlistmusicpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                myDialog.show();
            }
        });

    }











}
