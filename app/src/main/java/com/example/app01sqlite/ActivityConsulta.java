package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;

    EditText id, nombres, apellidos, edad, correo, direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        id = (EditText) findViewById(R.id.id);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        correo = (EditText) findViewById(R.id.txtCorreo);
        edad = (EditText) findViewById(R.id.txtEdad);
        direccion = (EditText) findViewById(R.id.txtDireccion);

        Button btnConsulta = (Button) findViewById(R.id.btnBuscar);
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });

        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });
    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String [] params = {id.getText().toString()};
        String [] fields = {
                Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.correo,
                Transacciones.edad,
                Transacciones.direccion
        };

        String whereCond = Transacciones.id+"=?";
    try{
        Cursor cdata = db.query(Transacciones.tablaPersonas,fields,whereCond,params,null,null,null);
        cdata.moveToFirst();

        nombres.setText(cdata.getString(0));
        apellidos.setText(cdata.getString(1));
        correo.setText(cdata.getString(2));
        edad.setText(cdata.getString(3));
        direccion.setText(cdata.getString(4));

        Toast.makeText(getApplicationContext(), "Resultado con exito", Toast.LENGTH_LONG).show();
    }catch(Exception ex){
        ClearScreen();
        Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
    }

    }

    private void Actualizar(){
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());

        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    private void Eliminar(){
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String whereCond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersonas, whereCond, params);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_LONG).show();
    }

    private void ClearScreen() {
        nombres.setText("");
        apellidos.setText("");
        correo.setText("");
        edad.setText("");
        direccion.setText("");
    }
}