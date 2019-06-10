package com.example.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSqLite conn = new ConexionSqLite(this, "bd_usuarios",null,1 );
    }

    public void onClick(View view){
        Intent miIntent = null;
        switch(view.getId()){
            case R.id.btnRegistro:
                miIntent = new Intent(this, RegistroUsuarios.class);
                break;
            case R.id.btConsulta:
                miIntent = new Intent(this, ConsultarUsuario.class);
                break;
            case R.id.conSpinner:
                miIntent = new Intent(this, ConsultaCombo.class);
                break;
            case R.id.conListView:
                miIntent = new Intent(this, ConsultaListView.class);
                break;
            case R.id.btnRegistrarMascota:
                miIntent = new Intent(this, RegistroMascota.class);
                break;
            case R.id.btnListaMascotas:
                miIntent = new Intent(this, ConsultaListaMascotas.class);
                break;

        }
        if(miIntent!=null){
            startActivity(miIntent);
        }
    }
}
