package com.aulasemcasa.nac01;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import info.hoang8f.widget.FButton;

public class JogoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvFichas;
    //private TextView tvSexo;

    private ImageView ivPersonagem;

    private ImageView ivSlot1, ivSlot2, ivSlot3;
    private Roda slot1, slot2, slot3;
    private FButton btJogar;
    public static final Random RANDOM = new Random();
    public static long randomLong( long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFichas = (TextView) findViewById(R.id.tvFichas);
        //tvSexo = (TextView) findViewById(R.id.tvSexo);

        ivPersonagem = (ImageView) findViewById(R.id.ivPersonagem);

        ivSlot1 = (ImageView) findViewById(R.id.ivSlot1 );
        ivSlot2 = (ImageView) findViewById(R.id.ivSlot2 );
        ivSlot3 = (ImageView) findViewById(R.id.ivSlot3 );
        btJogar = (FButton) findViewById(R.id.btJogar);

        Bundle bundle = getIntent().getExtras();

        String nome = bundle.getString("nome");
        String sexo = bundle.getString("sexo");
        String fichas = bundle.getString("fichas");

        tvNome.setText("Olá, " + nome);
        tvFichas.setText("Você tem " + fichas);

        if (sexo.equals("masculino")){
            ivPersonagem.setImageDrawable(ContextCompat.getDrawable(JogoActivity.this, R.drawable.link_icon));
            //tvSexo.setText("O sexo é " + sexo);
        }else if (sexo.equals("feminino")){
            ivPersonagem.setImageDrawable(ContextCompat.getDrawable(JogoActivity.this, R.drawable.zelda_icon));
            //tvSexo.setText("O sexo é " + sexo);
        }
    }

    public void jogar(View v) {

        MediaPlayer player = MediaPlayer.create(JogoActivity.this.getApplicationContext(), R.raw.startslot);
        //MediaPlayer player = new MediaPlayer.create(JogoActivity.this, R.raw.startslot);
        player.start();

        rodarSlot1();
        rodarSlot2();
        rodarSlot3();
        btJogar.setEnabled( false );
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                slot1.pararDeRodar();
                slot2.pararDeRodar();
                slot3.pararDeRodar();
                exibeResultado();
                btJogar.setEnabled( true );
            }
        }, 3000 );
    }
    private void exibeResultado() {
        if (slot1.indiceAtual == slot2.indiceAtual && slot2.indiceAtual == slot3.indiceAtual) {
            Toast.makeText (this, "Você acertou! Sério, parabéns, isso é muito difícil. +5 Fichas", Toast.LENGTH_SHORT).show();
        } else if (slot1.indiceAtual == slot2.indiceAtual || slot2.indiceAtual == slot3.indiceAtual
                || slot1.indiceAtual == slot3.indiceAtual) {
            Toast.makeText (this, "Quase acertou! + 2 Fichas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText (this, "Você perdeu! - 1 Ficha", Toast.LENGTH_SHORT).show();
        }
    }
    private void rodarSlot1() {
        slot1 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong (0, 200));
        slot1.start();
    }
    private void rodarSlot2() {
        slot2 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong (150, 400));
        slot2.start();
    }
    private void rodarSlot3() {
        slot3 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(300, 600));
        slot3.start();
    }
}