package com.example.database.utilidades;

public class Utilidades {


    //Constantes campos tablas

    public static final String TABLA_USUARIO = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIO ="create table "+TABLA_USUARIO+
            "("+CAMPO_ID+" int primary key, " +CAMPO_NOMBRE+" text, "+CAMPO_TELEFONO+" text)";

    public static final String INGRESO_ADMIN = "insert into " +TABLA_USUARIO+ " values(123456,'admin', '311315')";

    public static final String CONSULTA_BD = "select "+CAMPO_NOMBRE+", "+CAMPO_TELEFONO+" from "+
            TABLA_USUARIO+" where "+CAMPO_ID+" ="+CAMPO_ID;

     //db.execSQL("create table users(code int primary key, user text, password text)");
      //db.execSQL("insert into users values(01,'admin', 'admin')");

    //"select descripcion, precio from articulos where codigo =" + codigo, null    --para consulta
}
