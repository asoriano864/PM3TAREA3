package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBusqueda = (Button) findViewById(R.id.btnBusqueda);
        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent busqueda = new Intent(v.getContext(),ActivityConsulta.class);
                startActivity(busqueda);
            }
        });

        Button btnMapa = (Button) findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapa = new Intent(v.getContext(),ActivityCombo.class);
                startActivity(mapa);
            }
        });
        Button btnFot = (Button) findViewById(R.id.btnFoto);
        btnFot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foto = new Intent(v.getContext(),ActivityPhoto.class);
                startActivity(foto);
            }
        });

        Button btnListaPersonas = (Button) findViewById(R.id.btnListaPersonas);
        btnListaPersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListaPersonas = new Intent(v.getContext(),ActivityListView.class);
                startActivity(ListaPersonas);
            }
        });

        Button btnComboLista = (Button) findViewById(R.id.btnComboLista);
        btnComboLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Combo = new Intent(v.getContext(), ActivityCombo.class);
                startActivity(Combo);
            }
        });


    }

    public void clickNew(View view) {
        Intent intent = new Intent(this, ActivityIngresar.class);
        startActivity(intent);
    }
}