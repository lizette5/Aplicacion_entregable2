package com.example.aplicacion.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacion.R;
import com.example.aplicacion.ui.dashboard.MusicFragment;
import com.example.aplicacion.ui.notifications.NotificationsFragment;
import com.example.aplicacion.ui.times.TimesFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    View view;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);
        configureImageButton(view);
        return view;
    }
        /*homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textView11);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;*/


        private void configureImageButton(View view) {
            // TODO Auto-generated method stub
            ImageButton btn = (ImageButton) view.findViewById(R.id.btnkilo);
            ImageButton btn1= (ImageButton) view.findViewById(R.id.btnmapa);
            ImageButton btn2= (ImageButton) view.findViewById(R.id.btnmusica);
            ImageButton btn3= (ImageButton) view.findViewById(R.id.btnranking);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), TimesFragment.class);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "You Clicked the button!", Toast.LENGTH_LONG).show();

                }
            });
            btn1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NotificationsFragment.class);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "You Clicked the button!", Toast.LENGTH_LONG).show();

                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MusicFragment.class);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "You Clicked the button!", Toast.LENGTH_LONG).show();

                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),HomeFragment.class);//temporal
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(), "You Clicked the button!", Toast.LENGTH_LONG).show();

                }
            });

    }
}