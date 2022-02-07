package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.fragment.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;
    private ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    CircleImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.pro_pic);
        button = findViewById(R.id.button);

        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        setSupportActionBar(bind.toolbar);

        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
        bind.navView.setCheckedItem(R.id.nav_home);

        toggle = new ActionBarDrawerToggle(this, bind.drawer, bind.toolbar, R.string.open, R.string.close);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toggle.syncState();

        final Fragment[] fragment = {null};


        bind.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment[0] = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        bind.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment[0]);
                        break;
                    case R.id.nav_contact:
                        fragment[0] = new ContactFragment();
                        getSupportActionBar().setTitle("Contact Us");
                        bind.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment[0]);
                        break;
                    case R.id.nav_about:
                        fragment[0] = new AboutFragment();
                        getSupportActionBar().setTitle("About Us");
                        bind.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment[0]);
                        break;
                }
                return true;
            }
        });
    }

    public void callFragment(Fragment fragment) {
        FragmentManager man = getSupportFragmentManager();
        FragmentTransaction trans = man.beginTransaction();
        trans.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        trans.replace(R.id.framelayout, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }
}