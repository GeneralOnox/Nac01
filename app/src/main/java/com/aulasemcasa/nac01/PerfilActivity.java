package com.aulasemcasa.nac01;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class PerfilActivity extends AppCompatActivity {

    public ImageView ivPersonagem;

    public RadioGroup rgSexo;

    public EditText etNome;

    public Spinner spnFichas;

    public String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ivPersonagem = (ImageView) findViewById(R.id.ivPersonagem);

        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        etNome = (EditText) findViewById(R.id.etNome);

        spnFichas = (Spinner) findViewById(R.id.spnFichas);

    }

    public void escolherSexo(View view){
        int sexoSelecionado = rgSexo.getCheckedRadioButtonId();

        switch (sexoSelecionado){
            case R.id.rbMasculino:
                ivPersonagem.setImageDrawable(ContextCompat.getDrawable(PerfilActivity.this, R.drawable.link_icon));

                sexo = "masculino";

                break;
            case R.id.rbFeminino:
                ivPersonagem.setImageDrawable(ContextCompat.getDrawable(PerfilActivity.this, R.drawable.zelda_icon));

                sexo = "feminino";

                break;
        }

    }

    public void jogar(View view){

        //Mudar de tela Tela de Destino
        Intent intent = new Intent(this, JogoActivity.class);

        //Salva os valores para a próxima tela

        //Salva o nome
        String nome = etNome.getText().toString();

        //Salva o Spinner
        String fichas = spnFichas.getSelectedItem().toString();

        //Passa o valor para a outra tela
        intent.putExtra("nome", nome);
        intent.putExtra("sexo", sexo);
        intent.putExtra("fichas", fichas);

        //Iniciar a outra tela
        startActivity(intent) ;
        finish();
    }


}
