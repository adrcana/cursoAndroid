package com.example.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.utilidades.Utilidades;

public class RegistroUsuarios extends AppCompatActivity {

    EditText campoId, campoNombre, campoTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoId = (EditText)findViewById(R.id.et1);
        campoNombre= (EditText)findViewById(R.id.et2);
        campoTel= (EditText)findViewById(R.id.et3);
    }

    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios(){
        ConexionSqLite conn = new ConexionSqLite(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase(); //se abre base de dato para editar


        String id = campoId.getText().toString();
        String nombre = campoNombre.getText().toString();
        String telefono = campoTel.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()){

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("nombre", nombre);
            values.put("telefono", telefono);

            Long idResultante = db.insert("usuarios", "id", values);

            Toast.makeText(this, "Registro exitoso N°: "+idResultante, Toast.LENGTH_LONG).show();
            db.close();

            //limpiar campos de texto
            campoId.setText("");
            campoNombre.setText("");
            campoTel.setText("");

        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
