package com.example.playmix;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    boolean test = false;
    LoginButton loginButton;
    CallbackManager callbackManager;
    AuthCredential credentialTester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
       // dbUsers = FirebaseDatabase.getInstance().getReference("Users").child(auth.getInstance().getCurrentUser().getUid());
        firebaseUser = auth.getCurrentUser();
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        if(firebaseUser == null)
        {
            //loginButton = (LoginButton)findViewById(R.id.login_button);
            setUI();
        }
        else
        {
            //Intent myIntent = new Intent(MainActivity.this,ProfileActivity.class);
            //startActivity(myIntent);
        }

        setContentView(R.layout.activity_main);
    }


    private void setUI() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList("email"));
        //loginButton.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v){
    if (v==loginButton){
            User user = new User(auth.getCurrentUser().getEmail(),auth.getCurrentUser().getDisplayName());
            databaseReference = firebaseDatabase.getReference();
            DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError!=null){
                        Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Good",Toast.LENGTH_LONG).show();
                    }
                }
            };
        databaseReference.child("users").child(auth.getCurrentUser().getEmail()).setValue(user,completionListener);
    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void buttonclickLoginFb(View v)
    {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                HAndleFacebookToken(loginResult.getAccessToken());
                Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                //if (dbUsers.child("Users").child(auth.getCurrentUser().getUid()).)
                addUser(loginResult.getAccessToken());
                startActivity(new Intent(MainActivity.this, UserProfile.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "firebaseUser cancelled it", Toast.LENGTH_LONG).show();;

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addUser(AccessToken accessToken){
        databaseReference=firebaseDatabase.getReference();
        final User user = new User(auth.getCurrentUser().getEmail(),auth.getCurrentUser().getDisplayName());
        final String id = auth.getCurrentUser().getUid();
        final long[] status = new long[1];
        status[0] = 0;
        //user.setStatus(Long.parseLong(dbUsers.child("Users").child(id).child("status").toString()));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").hasChild(auth.getCurrentUser().getUid()))
                {
                    status[0] = Long.parseLong(dataSnapshot.child("Users").child(auth.getCurrentUser().getUid()).child("status").getValue().toString());
                }
                else {
                    user.setId(id);
                    user.setStatus(status[0]);
                    databaseReference.child("Users").child(id).setValue(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(this,"Added",Toast.LENGTH_LONG).show();
    }

    private void HAndleFacebookToken(AccessToken accessToken)
    {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        credentialTester=credential;
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(MainActivity.this, "UID collision", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Fail to register to firebase", Toast.LENGTH_LONG).show();
                }
                    else {
                    FirebaseUser myuserobj = auth.getCurrentUser();
                    //UpdateUI(myuserobj);
                    test = true;
                }
            }
            }
        );
    }
    private void validate(View view){
        if (test){
            Intent userScreen = new Intent(MainActivity.this , UserProfile.class);
        }
        else {
            Toast.makeText(getApplicationContext(),"blabla",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        LoginManager.getInstance().logOut();
        super.onStop();
    }
}
