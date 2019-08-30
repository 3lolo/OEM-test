package com.example.oemtest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oemtest.fragments.HomeFragment;
import com.example.oemtest.fragments.MapFragment;
import com.example.oemtest.fragments.WheelFragment;

public class MainActivity extends AppCompatActivity implements NavInterface {
//    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        container = findViewById(R.id.container);
        showFragment(new HomeFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        if (fm == null || fragment == null) return;
        fm.beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getCanonicalName())
                .addToBackStack(fragment.getClass().getCanonicalName())
                .commit();
    }


    @Override
    public void actionNavToWheel() {
        showFragment(new WheelFragment());
    }

    @Override
    public void actionNavToMap() {
        showFragment(new MapFragment());
    }
}
