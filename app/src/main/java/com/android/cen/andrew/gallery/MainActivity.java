package com.android.cen.andrew.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mBottomNavigationView = findViewById(R.id.bottom_nav_bar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = null;

                item.setChecked(true);
                if (item.getItemId() == R.id.basic) {
                    fragment = new BasicFragment();
                } else {
                    fragment = new AdvancedFragment();
                }

                fm.beginTransaction().replace(R.id.containers, fragment).commit();

                return false;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new BasicFragment()).commit();
    }
}
