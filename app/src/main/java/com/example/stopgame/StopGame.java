package com.example.stopgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.JsonUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;

public class StopGame extends AppCompatActivity {

    private TextView tvLetter;
    private ImageView ivStopbtn;
    private EditText name, cep, color, food, animal, car, object;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference ref;
    DatabaseReference mStopReference;
    String gameID, gameLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_game);

        tvLetter = findViewById(R.id.tvLetter);
        ivStopbtn = findViewById(R.id.ivStop);
        name = findViewById(R.id.etNome);
        cep = findViewById(R.id.etCep);
        color = findViewById(R.id.etCor);
        food = findViewById(R.id.etComida);
        animal = findViewById(R.id.etAnimal);
        car = findViewById(R.id.etCarro);
        object = findViewById(R.id.etObjeto);



        Intent intent = getIntent();
        gameID = intent.getStringExtra("ID");
        gameLetter = intent.getStringExtra("gameLetter");
        tvLetter.append(" "+ gameLetter.toUpperCase());






        mStopReference = FirebaseDatabase.getInstance().getReference()
                .child("ListParticipants").child(gameID);
        mStopReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ivStopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Nome esta vazio!", Toast.LENGTH_SHORT).show();
                } else if(cep.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo CEP esta vazio!", Toast.LENGTH_SHORT).show();
                } else if(color.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Cor esta vazio!", Toast.LENGTH_SHORT).show();
                } else if(food.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Comida esta vazio!", Toast.LENGTH_SHORT).show();
                } else if(animal.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Animal esta vazio!", Toast.LENGTH_SHORT).show();
                }else if(car.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Carro esta vazio!", Toast.LENGTH_SHORT).show();
                } else if(object.getText().toString().isEmpty()) {
                    Toast.makeText(StopGame.this, "Campo Objeto esta vazio!", Toast.LENGTH_SHORT).show();
                } else
                    {
                        ivStopbtn.setEnabled(false);

                        FormGame currentPlayer = new FormGame("STOP",
                                name.getText().toString(), cep.getText().toString(),
                                color.getText().toString(),  food.getText().toString(),  animal.getText().toString(),
                                car.getText().toString(), object.getText().toString());

                        addFormFirebase(currentPlayer);
                    }




            }

        });

    }

    private void addFormFirebase(FormGame playerInfo) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("ListParticipants").child(gameID).child(user.getUid()).setValue(playerInfo);
    }
}
