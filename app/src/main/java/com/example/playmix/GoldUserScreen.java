package com.example.playmix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class GoldUserScreen extends AppCompatActivity {

    ImageButton goldUser_game1;//Worms
    ImageButton goldUser_game2;//Mario
    ImageButton goldUser_game3;//Doom
    ImageButton goldUser_game4;//angry birds
    ImageButton goldUser_game5;//fifa15
    ImageButton goldUser_game6;//sonic
    Button showMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_user_screen);

        goldUser_game1 = (ImageButton) findViewById(R.id.imageButtonWWE);
        goldUser_game2 = (ImageButton) findViewById(R.id.imageButtonRayman);
        goldUser_game3 = (ImageButton) findViewById(R.id.imageButtonNBA);
        goldUser_game4 = (ImageButton) findViewById(R.id.imageButtonBattles);
        goldUser_game5 = (ImageButton) findViewById(R.id.imageButtonHarryPoter);
        goldUser_game6 = (ImageButton) findViewById(R.id.imageButtonWarCraft);
        showMore = (Button) findViewById(R.id.buttonShowMore);
        goldUser_game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        goldUser_game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        goldUser_game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        goldUser_game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        goldUser_game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        goldUser_game6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_game();
            }
        });
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_free_screen = new Intent(GoldUserScreen.this,FreeUserScreen.class);
                startActivity(to_free_screen);
            }
        });
    }





    private void download_game(){
        Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show();
    }
}
