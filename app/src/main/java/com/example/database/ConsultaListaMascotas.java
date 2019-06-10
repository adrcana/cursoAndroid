package com.example.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.database.entidades.Mascota;

import java.util.ArrayList;

public class ConsultaListaMascotas extends AppCompatActivity {

    ListView listViewMascota;
    ArrayList <String> listaInformacion;
    ArrayList<Mascota> listaMascotas;

    ConexionSqLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista_mascotas);

        conn = new ConexionSqLite(this, "bd_usuarios", null, 1);

        listViewMascota = (ListView)findViewById(R.id.listView2);

        consultarListaMascotas();

        ArrayAdapter adaptador = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewMascota.setAdapter(adaptador);

        listViewMascota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mascota mascota = listaMascotas.get(position);

                Intent intent = new Intent(ConsultaListaMascotas.this, DetalleMascota.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("mascota", mascota);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void consultarListaMascotas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        
        Mascota mascota = null;
        listaMascotas = new ArrayList<Mascota>();
        
        // select * from mascotas

        Cursor cursor = db.rawQuery("select * from mascotas", null);
        
        while (cursor.moveToNext()){
            
            mascota = new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));
            
            listaMascotas.add(mascota);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaMascotas.size();i++){
            listaInformacion.add(listaMascotas.get(i).getIdMascota()+" - "
                    +listaMascotas.get(i).getNombreMascota());
        }
    }
}
