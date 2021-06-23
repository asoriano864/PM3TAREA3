package com.example.app01sqlite;

import android.database.Cursor;
import android.os.Bundle;

import com.example.app01sqlite.Tablas.Personas;
import com.example.app01sqlite.transacciones.Transacciones;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner comboPersona;
    EditText txtNombre, txtApellido, txtCorreo;

    ArrayList<String> listaPersonas;
    ArrayList<Personas> lista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        comboPersona = (Spinner) findViewById(R.id.spinner);
        txtNombre = (EditText) findViewById(R.id.txtNombresCombo);
        txtApellido = (EditText) findViewById(R.id.txtApellidosCombo);
        txtCorreo = (EditText) findViewById(R.id.txtCorreoCombo);


        ObtenerListaPersonas();
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);
        comboPersona.setAdapter(adp);

        comboPersona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void ObtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaPersonas, null);

        while(cursor.moveToNext()){
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombres(cursor.getString(1));
            persona.setApellidos(cursor.getString(2));
            persona.setEdad(cursor.getInt(3));
            persona.setCorreo(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));

            lista.add(persona);
        }

        cursor.close();


    }

    private void fillComb() {
        listaPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaPersonas.add(lista.get(i).getId() + "  |  "
                    + lista.get(i).getNombres() + " "
                    + lista.get(i).getApellidos());
        }
    }





}