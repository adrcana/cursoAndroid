package com.example.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.database.entidades.Usuario;

public class DetalleUsuario extends AppCompatActivity {

    TextView campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        campoId = (TextView)findViewById(R.id.cam_ID);
        campoNombre = (TextView)findViewById(R.id.camNom);
        campoTelefono = (TextView)findViewById(R.id.camTel);

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;

        if(objetoEnviado!=null){
            user = (Usuario)objetoEnviado.getSerializable("usuario");

            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());
        }
    }
}
