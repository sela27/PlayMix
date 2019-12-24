package com.example.playmix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FreeUserScreen extends AppCompatActivity {

    ImageButton freeUser_game1;//Worms
    ImageButton freeUser_game2;//Mario
    ImageButton freeUser_game3;//Doom
    ImageButton freeUser_game4;//angry birds
    ImageButton freeUser_game5;//fifa15
    ImageButton freeUser_game6;//sonic
    Button show_lib_gold;
    boolean gold_unlocked;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_user_screen);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        freeUser_game1 = (ImageButton)findViewById(R.id.imageButtonWorms);
        freeUser_game2 = (ImageButton)findViewById(R.id.imageButtonMario);
        freeUser_game3 = (ImageButton)findViewById(R.id.imageButtonDoom);
        freeUser_game4 = (ImageButton)findViewById(R.id.imageButtonBirds);
        freeUser_game5 = (ImageButton)findViewById(R.id.imageButtonFifa);
        freeUser_game6 = (ImageButton)findViewById(R.id.imageButtonSonic);
        show_lib_gold = findViewById(R.id.buttonToGoldlibrary);

        freeUser_game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Worms").setValue(1);
                download_game();
            }
        });
        freeUser_game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Mario").setValue(1);
                download_game();
            }
        });
        freeUser_game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Doom").setValue(1);
                download_game();
            }
        });
        freeUser_game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Angry Birds").setValue(1);
                download_game();
            }
        });
        freeUser_game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Fifa 15").setValue(1);
                download_game();
            }
        });
        freeUser_game6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList").child("Sonic").setValue(1);
                download_game();
            }
        });
        show_lib_gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_query();
                if (!gold_unlocked){
                    Intent free2gold = new Intent(FreeUserScreen.this,GoldUserScreen.class);
                    startActivity(free2gold);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please upgrade subscription first",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void status_query(){
        databaseReference = firebaseDatabase.getReference("Users").child(auth.getCurrentUser().getUid());
        final Long[] status = new Long[1];
        Query status_user_query = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid())
                .orderByChild("status")
                ;
        status_user_query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),"status = "+dataSnapshot.child("status").getValue(Long.class),Toast.LENGTH_LONG).show();
                status[0] = dataSnapshot.child("status").getValue(Long.class);
                gold_unlocked = dataSnapshot.child("status").getValue(Long.class) != 0 ? false : true;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"status checking was interrupted",Toast.LENGTH_LONG).show();
                status[0] = Long.valueOf(0);
            }
        });
        return;
    }

    private void download_game(){
        Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show();
    }

}
