package com.example.stopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultGame extends AppCompatActivity {
    private TextView tvNome, tvCep, tvCor, tvComida, tvAnimal, tvCarro, tvObjeto;
    private TextView tvNomeParticipant2, tvNomeParticipant3, tvNomeParticipant4;
    private TextView vtCepParticipant2, tvCepParticipant3, tvCepParticipant4;
    private TextView tvCorParticipant2, tvCorParticipant3, tvCorParticipant4;
    private TextView tvComidaParticipant2, tvComidaParticipant3, tvNComidaParticipant4;
    private TextView tvAnimalParticipant2, tvAnimalParticipant3, tvAnimalParticipant4;
    private TextView tvCarroParticipant2, tvCarroParticipant3, tvCarroParticipant4;
    private TextView tvObjetoParticipant2, tvObjetoParticipant3, tvObjetoParticipant4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_game);


    }
}
