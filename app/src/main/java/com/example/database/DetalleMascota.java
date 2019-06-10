package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.entidades.Mascota;

public class DetalleMascota extends AppCompatActivity {

    TextView campoIdMascota, campoNombreMascota, campoRaza;
    TextView campoIdPersona, campoNombrePersona, campoTelefono;

    ConexionSqLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conn= new ConexionSqLite(this, "bd_usuarios", null,1);

        campoIdPersona = (TextView) findViewById(R.id.camID);
        campoNombrePersona = (TextView) findViewById(R.id.camNomUser);
        campoTelefono = (TextView) findViewById(R.id.camTel2);

        campoIdMascota = (TextView) findViewById(R.id.camIDmascota);
        campoNombreMascota = (TextView) findViewById(R.id.camNomMascota);
        campoRaza = (TextView) findViewById(R.id.camRaza);

        Bundle objetoEnviado = getIntent().getExtras();
        Mascota mascota = null;

        if (objetoEnviado!=null){
            mascota = (Mascota)objetoEnviado.getSerializable("mascota");

            campoIdMascota.setText(mascota.getIdMascota().toString());
            campoNombreMascota.setText(mascota.getNombreMascota().toString());
            campoRaza.setText(mascota.getRaza().toString());

            consultarPernona(mascota.getIdDuenio());


        }
    }

    private void consultarPernona(Integer idDuenio) {

        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {idDuenio.toString()};
        String[] campos = {"nombre", "telefono"};

        Toast.makeText(this, "el documento "+idDuenio, Toast.LENGTH_SHORT).show();

        try {
            Cursor cursor = db.query
                    ("usuarios", campos, "id=?", parametros, null,null,null);
            cursor.moveToFirst();
            campoIdPersona.setText(idDuenio.toString());
            campoNombrePersona.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, "el documento no existe", Toast.LENGTH_SHORT).show();

            campoNombrePersona.setText("");
            campoTelefono.setText("");
        }

    }
}
