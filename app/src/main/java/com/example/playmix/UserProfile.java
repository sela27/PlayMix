package com.example.playmix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    Button store_browse;
    Button upgrade;
    Button Exit;
    Button myLib;
    boolean gold_unlocked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_user);
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();
        store_browse = (Button) findViewById(R.id.button2); //My PlayMix Button
        status_query();
        //Toast.makeText(this,"user status = "+user_status.longValue(),Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"user status Long valof = "+Long.valueOf(user_status),Toast.LENGTH_LONG).show();

        store_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gold_unlocked) {
                    Intent freeLibrary = new Intent(UserProfile.this, FreeUserScreen.class);
                    startActivity(freeLibrary);
                }

                else { //change to else if status ==1
                    Intent goldLibrary = new Intent(UserProfile.this,GoldUserScreen.class);
                    startActivity(goldLibrary);
                }

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
        myLib = findViewById(R.id.buttonMyLib);

        myLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this,UserLibrary.class);
                startActivity(intent);
            }
        });

    }

    private void status_toast_check(){
        /*
        String stat1 = databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("status").getKey();
        String stat2 = databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("status").toString();
        String stat3 = databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("status").getRef().getKey();
        String stat4 = databaseReference.child("Users").child(auth.getCurrentUser().getUid()).child("status").getRef().toString();
        Toast.makeText(this,"get key" +stat1,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"tostring "+stat2,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"get Ref get key "+stat3,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"get Ref tostring "+stat4,Toast.LENGTH_LONG).show();
        */
        ValueEventListener valueEventListener;
        databaseReference = firebaseDatabase.getReference("Users").child(auth.getCurrentUser().getUid());
        final Long[] status = new Long[1];
        final String[] user_name = new String[1];
        Query status_user_query = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid())
                .orderByChild("status")
                ;

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                user_name[0] = dataSnapshot.child("Users").child(auth.getCurrentUser().getUid()).child("name").getValue(String.class).toString();
                status[0] = dataSnapshot.child("Users").child(auth.getCurrentUser().getUid()).child("status").getValue(Long.class);
                Toast.makeText(getApplicationContext(),""+status[0],Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),""+user_name[0],Toast.LENGTH_LONG).show();
            }
            else {
             //   status[0] = dataSnapshot.child("Users").child(auth.getCurrentUser().getUid()).child("status").getKey(Long.class);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
        //Toast.makeText(this,""+status[0],Toast.LENGTH_LONG).show();
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
}
