package com.developerandroid.convertortemperatura;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mingo on 6/28/16.
 */
public class UsuariosSQLiteHelper extends SQLiteOpenHelper{
    // Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Consultas (valor INTEGER)";

    public UsuariosSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        // Envia al constructor de la super clase
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Consultas");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);

    }

}
