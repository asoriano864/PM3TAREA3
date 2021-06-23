package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText bxNombres;
    EditText bxApellidos;
    EditText bxEdad;
    EditText bxCorreo;
    EditText bxDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

         bxNombres = (EditText) findViewById(R.id.txtNombres);
         bxApellidos = (EditText) findViewById(R.id.txtApellidos);
         bxEdad = (EditText) findViewById(R.id.txtEdad);
         bxCorreo = (EditText) findViewById(R.id.txtCorreo);
         bxDireccion = (EditText) findViewById(R.id.txtDireccion);

        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);
        
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }
        });

    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, bxNombres.getText().toString());
        valores.put(Transacciones.apellidos, bxApellidos.getText().toString());
        valores.put(Transacciones.correo, bxCorreo.getText().toString());
        valores.put(Transacciones.edad, bxEdad.getText().toString());
        valores.put(Transacciones.direccion, bxDireccion.getText().toString());

        Long Resultado = db.insert(Transacciones.tablaPersonas,Transacciones.id,valores);
        //toast
        Toast.makeText(getApplicationContext(), "Registro creado", Toast.LENGTH_LONG).show();

        ClearScreen();

        db.close();

    }

    private void ClearScreen() {
        bxNombres.setText("");
        bxApellidos.setText("");
        bxCorreo.setText("");
        bxDireccion.setText("");
        bxEdad.setText("");

    }
}