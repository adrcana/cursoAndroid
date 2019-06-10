package com.example.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database.entidades.Usuario;

import java.util.ArrayList;

public class RegistroMascota extends AppCompatActivity {

    EditText campoRaza, campoNombreMascota;
    Spinner comboDuenio;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSqLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        campoRaza = (EditText)findViewById(R.id.et_raza);
        campoNombreMascota = (EditText)findViewById(R.id.et_nombreMascota);
        comboDuenio = (Spinner)findViewById(R.id.spinnerMascota);

        conn = new ConexionSqLite(this, "bd_usuarios", null, 1);

       consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaPersonas);

        comboDuenio.setAdapter(adaptador);

        comboDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void registrarMascota(){

        SQLiteDatabase db = conn.getWritableDatabase();

        String nameMascota = campoNombreMascota.getText().toString();
        String raza = campoRaza.getText().toString();

        if(!nameMascota.isEmpty() && !raza.isEmpty()){

            ContentValues values = new ContentValues();
            values.put("nombre_mascota", nameMascota);
            values.put("raza_mascota", raza);

            int idCombo = (int)comboDuenio.getSelectedItemId();

            if (idCombo!=0){
                Log.i("TAMAÑO", personasList.size()+"");
                Log.i("id combo", idCombo+"");
                Log.i("id combo -1", (idCombo-1)+""); // se resta 1 ya que se quiere obtener la posicion de la lista, no del combo

                int idDuenio = personasList.get(idCombo-1).getId();
                Log.i("id dueño", idDuenio+"");

                values.put("id_usuario", idDuenio);

                Long idResultante = db.insert("mascotas", "id_mascota", values);

                Toast.makeText(this,"Id registro: "+idResultante,Toast.LENGTH_SHORT).show();
                db.close();

                //limpiar campos de texto
                campoNombreMascota.setText("");
                campoRaza.setText("");

            }else{
                Toast.makeText(this,"Selecciona un dueño",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;
        personasList = new ArrayList<Usuario>();

        //select * from usuarios
        Cursor cursor = db.rawQuery("Select * from usuarios", null);

        while(cursor.moveToNext()){

            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personasList.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i=0; i<personasList.size(); i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btRegistrarMascota:
                registrarMascota();
                break;
        }
    }
}
