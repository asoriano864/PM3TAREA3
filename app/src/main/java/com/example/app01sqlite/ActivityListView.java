package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.app01sqlite.Tablas.Personas;
import com.example.app01sqlite.transacciones.Transacciones;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listaPersonas;
    ArrayList <Personas> Lista;
    ArrayList<String> ArregloPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listaPersonas = (ListView) findViewById(R.id.ListaPersonas);

        ObtenerListaPersonas();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listaPersonas.setAdapter(adp);


    }

    private void ObtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;

        Lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaPersonas, null);

        while(cursor.moveToNext()){
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombres(cursor.getString(1));
            persona.setApellidos(cursor.getString(2));
            persona.setEdad(cursor.getInt(3));
            persona.setCorreo(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));
        }

        Lista.add(persona);
        fillListaPersonas();
    }

    private void fillListaPersonas() {
        ArregloPersonas = new ArrayList<String>();
        for(int i= 0; i < Lista.size();i++){
            ArregloPersonas.add(Lista.get(i).getId() + " | " + Lista.get(i).getNombres() + " | " + Lista.get(i).getApellidos());
        }

    }


}