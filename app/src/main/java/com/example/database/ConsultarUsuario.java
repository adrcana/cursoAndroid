package com.example.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.utilidades.Utilidades;

public class ConsultarUsuario extends AppCompatActivity {

    EditText campoId, campoNom, campoTelef;

    ConexionSqLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);

        conn = new ConexionSqLite(this, "bd_usuarios",null,1 );

        campoId = (EditText)findViewById(R.id.gID);
        campoNom= (EditText)findViewById(R.id.gNombre);
        campoTelef = (EditText)findViewById(R.id.gTelef);

    }

    public void Elegir(View view){

        switch (view.getId()){
            case R.id.btBuscar:
                consultar();
                break;
            case R.id.btActualizar:
                actualizarUsuario();
                break;
            case R.id.btEliminar:
                eliminar();
                break;
            case R.id.btIrRegistro:
                Intent miIntent = new Intent(this, RegistroUsuarios.class);
                startActivity(miIntent);
                finish();
                break;


        }
    }

    private void consultar() {

        SQLiteDatabase db = conn.getReadableDatabase();
        //SQLiteDatabase db = conn.getReadableDatabase();

        //String[] parametros = {campoId.getText().toString()};
        //String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        String ident = campoId.getText().toString();

        if(!ident.isEmpty()){

            try {

                Cursor cursor = db.rawQuery("select nombre, telefono from usuarios where id = " +
                        ident, null);

                // Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID+"?", parametros, null, null, null);

                cursor.moveToFirst();
                campoNom.setText(cursor.getString(0));
                campoTelef.setText(cursor.getString(1));
                cursor.close();

            }catch (Exception e){
                Toast.makeText(this, "El Id no existe", Toast.LENGTH_SHORT).show();
                limpiar();
            }

        }else{
            Toast.makeText(this, "Debes ingresar el Id", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String id = campoId.getText().toString();
        String nombre = campoNom.getText().toString();
        String telefono = campoTelef.getText().toString();

        if (!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()){

            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("nombre", nombre);
            values.put("telefono", telefono);

            int cantidad = db.update("usuarios", values, "id =" +id, null);
            db.close();

            if (cantidad == 1){
                Toast.makeText(this, "actualizacion satisfactoria", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminar() {

        SQLiteDatabase db = conn.getWritableDatabase();

        String ident = campoId.getText().toString();

        if (!ident.isEmpty()){

            int cantidad = db.delete("usuarios", "id ="+ ident,null);
            db.close();

            if (cantidad == 1){

                Toast.makeText(this, "usuario eliminado", Toast.LENGTH_SHORT).show();
                limpiar();

            }else{
                Toast.makeText(this, "el usuario no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "debes ingresar el ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar() {
        campoNom.setText("");
        campoTelef.setText("");
    }

}
