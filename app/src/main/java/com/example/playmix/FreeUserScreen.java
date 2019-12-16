package com.example.playmix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class FreeUserScreen extends AppCompatActivity {

    ImageButton freeUser_game1;//Worms
    ImageButton freeUser_game2;//Mario
    ImageButton freeUser_game3;//Doom
    ImageButton freeUser_game4;//angry birds
    ImageButton freeUser_game5;//fifa15
    ImageButton freeUser_game6;//sonic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_user_screen);
        freeUser_game1 = (ImageButton)findViewById(R.id.imageButtonWorms);
        freeUser_game2 = (ImageButton)findViewById(R.id.imageButtonMario);
        freeUser_game3 = (ImageButton)findViewById(R.id.imageButtonDoom);
        freeUser_game4 = (ImageButton)findViewById(R.id.imageButtonBirds);
        freeUser_game5 = (ImageButton)findViewById(R.id.imageButtonFifa);
        freeUser_game6 = (ImageButton)findViewById(R.id.imageButtonSonic);

        freeUser_game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        freeUser_game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        freeUser_game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        freeUser_game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        freeUser_game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        freeUser_game6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
    }

    private void download_game(){
        Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show();
    }
}
