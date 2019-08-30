package com.example.oemtest.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oemtest.NavInterface;
import com.example.oemtest.R;

public class HomeFragment extends Fragment {
    private NavInterface navInterface;
    private Button btnWheel;
    private Button btnMap;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavInterface) {
            navInterface = (NavInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnWheel = view.findViewById(R.id.btnWheel);
        btnMap = view.findViewById(R.id.btnMap);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnWheel.setOnClickListener(v -> navInterface.actionNavToWheel());
        btnMap.setOnClickListener(v -> navInterface.actionNavToMap());
    }
}
