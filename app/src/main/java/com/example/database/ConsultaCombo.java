package com.example.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.database.entidades.Usuario;

import java.util.ArrayList;

public class ConsultaCombo extends AppCompatActivity {

    Spinner comboPersonas;
    TextView twNombre, twId, twTelefono;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSqLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn = new ConexionSqLite(this, "bd_usuarios", null, 1);

        comboPersonas = (Spinner)findViewById(R.id.spinnerConsulta);

        twId = (TextView)findViewById(R.id.tvID);
        twNombre = (TextView)findViewById(R.id.tvNom);
        twTelefono = (TextView)findViewById(R.id.tvTel);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaPersonas);

        comboPersonas.setAdapter(adaptador);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position !=0){

                    twId.setText("Identificacion: "+personasList.get(position - 1).getId());
                    twNombre.setText("Nombre :"+personasList.get(position-1).getNombre());
                    twTelefono.setText("Telefono: "+personasList.get(position-1).getTelefono());

                }else{

                    twId.setText("");
                    twNombre.setText("");
                    twTelefono.setText("");

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;
        personasList = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("select * from usuarios", null);

        while (cursor.moveToNext()){   // recorrer los registros de la bd

            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            //logs por si se quiere ver informacion en consola

            //Log.i("id", persona.getId());
            //Log.i("nombre", persona.getNombre());
            //Log.i("telefono", persona.getTelefono());

            personasList.add(persona);
        }

        obtenerLista();

    }

    private void obtenerLista() {

        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i=0; i < personasList.size(); i++){
            listaPersonas.add(personasList.get(i).getId() +" - "+personasList.get(i).getNombre());
        }
    }
}
