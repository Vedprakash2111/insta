package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.insta.home.homeFragment;
import com.example.insta.like.likeFragment;
import com.example.insta.post.postFragment;
import com.example.insta.profile.profileFragment;
import com.example.insta.search.searchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ImageView home,search,post,like,profile;
    FrameLayout frameLayout_container;
    Fragment selectedFragment = null;
    FirebaseAuth user;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home);
        search= findViewById(R.id.search);
        post=findViewById(R.id.post);
        like= findViewById(R.id.like);
        profile = findViewById(R.id.profile);
        frameLayout_container= findViewById(R.id.fragment_container);
        user= FirebaseAuth.getInstance();
        currentUser= user.getCurrentUser();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new homeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                home.setImageResource(R.drawable.home_fill);
                search.setImageResource(R.drawable.search_outline);
                post.setImageResource(R.drawable.add_outline);
                like.setImageResource(R.drawable.heart_outline);
                profile.setImageResource(R.drawable.profile_outline);

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new searchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                home.setImageResource(R.drawable.home_outline);
                search.setImageResource(R.drawable.search_fill);
                post.setImageResource(R.drawable.add_outline);
                like.setImageResource(R.drawable.heart_outline);
                profile.setImageResource(R.drawable.profile_outline);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new postFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                home.setImageResource(R.drawable.home_outline);
                search.setImageResource(R.drawable.search_outline);
                post.setImageResource(R.drawable.add_fill);
                like.setImageResource(R.drawable.heart_outline);
                profile.setImageResource(R.drawable.profile_outline);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new likeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                home.setImageResource(R.drawable.home_outline);
                search.setImageResource(R.drawable.search_outline);
                post.setImageResource(R.drawable.add_outline);
                like.setImageResource(R.drawable.heart_fill);
                profile.setImageResource(R.drawable.profile_outline);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = new profileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                home.setImageResource(R.drawable.home_outline);
                search.setImageResource(R.drawable.search_outline);
                post.setImageResource(R.drawable.add_outline);
                like.setImageResource(R.drawable.heart_outline);
                profile.setImageResource(R.drawable.profile);
            }
        });

        if(selectedFragment == null){
            selectedFragment = new homeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            home.setImageResource(R.drawable.home_fill);
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser==null){
            Intent login = new Intent(MainActivity.this,loginActivity2.class);
            startActivity(login);
            MainActivity.this.finish();
        }
    }
}

