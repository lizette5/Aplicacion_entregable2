package com.example.aplicacion.ui.times;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacion.Estadistica;
import com.example.aplicacion.LoginActivity;
import com.example.aplicacion.PrimerFragment;
import com.example.aplicacion.R;

public class TimesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    Button siguiente3;
    public TimesFragment() {
        // Required empty public constructor
    }


    public static TimesFragment newInstance(String param1, String param2) {
        TimesFragment fragment = new TimesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_times, container, false);
        siguiente3=(Button)view.findViewById(R.id.siguiente3);
        siguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Estadistica.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
  /*  public void Siguiente3(View view){
        Intent paso=new Intent(this, Estadistica.class);
        startActivity(paso);
    }*/

    /*
    private TimesViewModel timesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        timesViewModel =
                new ViewModelProvider(this).get(TimesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_times, container, false);
        final TextView textView = root.findViewById(R.id.text_times);
        timesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return inflater.inflate(R.layout.fragment_times, container, false);

    }*/

}