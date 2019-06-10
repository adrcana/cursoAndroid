package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.database.utilidades.Utilidades;

public class ConexionSqLite extends SQLiteOpenHelper {

    public ConexionSqLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        //db.execSQL(Utilidades.INGRESO_ADMIN);

        db.execSQL("create table usuarios(id Integer primary key, nombre text, telefono text)");
        db.execSQL("create table mascotas(id_mascota Integer primary key autoincrement," +
                " nombre_mascota text, raza_mascota text, id_usuario Integer)");

        //db.execSQL("insert into usuarios values(100,'admin', '567')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios"); //si existe una version antigua la borre
        db.execSQL("drop table if exists mascotas");
        onCreate(db); // y la vuelve a crear
    }
}
