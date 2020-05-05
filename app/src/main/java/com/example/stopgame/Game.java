package com.example.stopgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game extends AppCompatActivity {
    private String gameID, gameLetter, tempLetter;
    private ListView listParticipants;
    private TextView tvGameID;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    private DatabaseReference mListReference;
    private DatabaseReference mStatus;
    private ArrayAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        listParticipants = findViewById(R.id.listParticipants);
        tvGameID = findViewById(R.id.tvgameID);
        Button btnStart = findViewById(R.id.btnStart);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mStatus = FirebaseDatabase.getInstance().getReference();


        Intent intent = getIntent();
        gameID = intent.getStringExtra("ID");
        mStatus.child("ListParticipants").child(gameID).child("GameStatus").setValue("Waiting");




        btnStart.setVisibility(View.INVISIBLE);

        if(intent.getStringExtra("newgame") != null) {
            if(intent.getStringExtra("newgame").equals("newgame")) {

                btnStart.setVisibility(View.VISIBLE);

                // iniatilize a letter for the Game
                tempLetter = generateLetter();

            }
        }


        addUserFirebase();



            mListReference = FirebaseDatabase.getInstance().getReference()
                    .child("ListParticipants").child(gameID).child("users");
            final ArrayList<String> userList = new ArrayList<>();

            mListReference.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.exists()) {
                        tvGameID.setText("Sala Inexistente");

                    } else {

                        if(!userList.isEmpty()) {
                            userList.clear();
                        }
                        for (DataSnapshot users : dataSnapshot.getChildren()) {
                            String user = users.getValue().toString();
                            userList.add(user);
                            adapter.notifyDataSetChanged();
                            tvGameID.setText("ID: " + gameID);

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    databaseError.getMessage();
                }

            });



            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, userList);
            listParticipants.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Send info the have Started
                mStatus = FirebaseDatabase.getInstance().getReference();
                mStatus.child("ListParticipants").child(gameID).child("GameStatus").setValue("Playing");
//
            }
        });


        mStatus = FirebaseDatabase.getInstance().getReference()
                .child("ListParticipants").child(gameID).child("GameStatus");
        mStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    if(dataSnapshot.getValue(String.class).contains( "Playing"))
                    {
                        gameLetter = tempLetter;
                        Intent intent = new Intent(Game.this, StopGame.class);
                        intent.putExtra("ID", gameID);
                        intent.putExtra("gameLetter", gameLetter);
                        startActivity(intent);
//
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        }






    private void addUserFirebase() {
        String currentUser = mAuth.getCurrentUser().getDisplayName();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put(user.getUid(), currentUser);

        mDatabase.child("ListParticipants").child(gameID).child("users").updateChildren(hopperUpdates);

    }

    private String generateLetter() {
        // Generate a Letter for play
        Random r = new Random();
        int Low = 1;
        int High = 27;
        int rn = r.nextInt(High-Low) + Low;
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String gameLetter = alphabet[rn-1];
        return gameLetter;

    }




}



