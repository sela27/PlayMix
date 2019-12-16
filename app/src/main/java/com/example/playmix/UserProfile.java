package com.example.playmix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    Button library;
    Button upgrade;
    Button Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_user);
        library = (Button) findViewById(R.id.button2); //My PlayMix Button

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent freeLibrary = new Intent(UserProfile.this,FreeUserScreen.class);
                startActivity(freeLibrary);
            }
        });
        upgrade = (Button) findViewById( R.id.buttonUpgrade);
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserProfile.this,UpgradeSub.class);
                startActivity(intent);
            }
        });

        Exit = (Button) findViewById(R.id.buttonLogOut);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

}
