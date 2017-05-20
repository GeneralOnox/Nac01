package com.aulasemcasa.nac01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void perfil(View view){

        //Mudar de tela Tela de Destino
        Intent intent = new Intent(this, PerfilActivity.class) ;

        //Iniciar a outra tela
        startActivity(intent) ;

        finish();

    }

    public void sobre(View view){

        //Mudar de tela Tela de Destino
        Intent intent = new Intent(this, SobreActivity.class) ;

        //Iniciar a outra tela
        startActivity(intent) ;

        finish();

    }

}
