package com.example.memberajv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.memberajv.Fragment.BookmarkFragment;
import com.example.memberajv.Fragment.HomeFragment;
import com.example.memberajv.Fragment.OtherFragment;
import com.example.memberajv.Fragment.SearchFragment;
import com.example.memberajv.Fragment.TVFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());

        BottomNavigationView botNav = findViewById(R.id.botNav);
        botNav.setOnNavigationItemSelectedListener(this);


    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                break;
            case R.id.nav_tv:
                fragment = new TVFragment();
                break;
            case R.id.nav_koleksiku:
                fragment = new BookmarkFragment();
                break;
            case R.id.nav_lainnya:
                fragment = new OtherFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
