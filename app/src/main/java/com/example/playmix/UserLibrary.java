package com.example.playmix;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserLibrary extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference dbUsers;
    FirebaseDatabase database;
    LinkedList<Integer> games_pictures;
    LinkedList<String> games_names;
    ListView listView;
    ArrayList<Game> games;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_library);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        dbUsers = database.getReference("Users").child(auth.getCurrentUser().getUid()).child("GamesList");
        games_pictures = new LinkedList<>();
        games_names = new LinkedList<>();
        games = new ArrayList<>();
        listView = findViewById(R.id.listView);
        //listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
       //dbUsers.child("Users").child(auth.getCurrentUser().getUid()).child("GamesList")

        dbUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        String g = snapshot.getKey();
                        games_names.add(g);
                        //String picture = "R.drawable.free_usr_game1";
                        //games_pictures.add(Integer.parseInt(picture));
                        Game game = new Game(g);
                        games.add(game);
                    }
                }
                else {
                    Toast.makeText(UserLibrary.this,"No Games Added Yet",Toast.LENGTH_LONG).show();
                }
                listView.setAdapter(new MyAdapter(UserLibrary.this,games_pictures,games_names));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<Integer> pictures;
        List<String> names;

        MyAdapter(Context c, LinkedList<Integer> pictures, LinkedList<String> names) {
            super(c,R.layout.row, R.id.textView1, names);
            this.context = c;
            this.pictures = pictures;
            this.names = names;
        }

        @NonNull
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(pictures.get(position));
            myTitle.setText(names.get(position));
            myDescription.setText("The Wonderful Game Of: " + names.get(position));
            return row;
        }
    }
}
