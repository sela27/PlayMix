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
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseUser user;
    boolean test = false;
    LoginButton loginButton;
    CallbackManager callbackManager;
    AuthCredential credentialTester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        if(user == null)
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
        //loginButton.setOnClickListener();
        loginButton.setPermissions(Arrays.asList("email"));

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
                startActivity(new Intent(MainActivity.this,FreeUser.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "user cancelled it", Toast.LENGTH_LONG).show();;

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void HAndleFacebookToken(AccessToken accessToken)
    {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        credentialTester=credential;
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser myuserobj = auth.getCurrentUser();
                    //UpdateUI(myuserobj);
                    test = true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fail to register to firebase", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void validate(View view){
        if (test){
            Intent userScreen = new Intent(MainActivity.this , FreeUser.class);
        }
        else {
            Toast.makeText(getApplicationContext(),"blabla",Toast.LENGTH_LONG).show();
        }
    }
}
