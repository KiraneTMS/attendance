package com.example.attendance;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;


import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button logoutBtn;
    BottomSheetBehavior bottomSheetBehavior;

    FirebaseAuth fAuth;

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_ABOUT_US = 2;
    private static final int POS_LOGOUT = 3;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        logoutBtn = findViewById(R.id.logoutBtn);
        fAuth = FirebaseAuth.getInstance();
//        logout();
//        logoutBtn.setOnClickListener(view -> {
//            logout();
//        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        slidingRootNav = new SlidingRootNavBuilder(this)
//                .withDragDistance(180)
//                .withRootViewScale(0.75f)
//                .withRootViewElevation(25)
//                .withToolbarMenuToggle(toolbar)
//                .withMenuOpened(false)
//                .withContentClickableWhenMenuOpened(false)
//                .withSavedState(savedInstanceState)
//                .withMenuLayout(R.layout.drawer_menu)
//                .inject();
//
//        screenIcons = loadScreenIcons();
//        screenTitles = loadScreenTitles();

//        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
//                createItemFor(POS_CLOSE),
//                createItemFor(POS_DASHBOARD).setChecked(true),
//                createItemFor(POS_ABOUT_US),
//                new SpaceItem(260),
//                createItemFor(POS_LOGOUT)
//        ));
//        adapter.setListener(this);
//
//        RecyclerView list = findViewById(R.id.drawerList);
//        list.setNestedScrollingEnabled(false);
//        list.setLayoutManager(new LinearLayoutManager(this));
//        list.setAdapter(adapter);
//
//        adapter.setSelected(POS_DASHBOARD);

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    private void logout() {
        fAuth.signOut();
        startActivity(new Intent(MainActivity.this, SignIn.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();
//        if (currentUser != null){
//            startActivity(new Intent(MainActivity.this, Register.class));
//        }
    }

}