package com.developerandroid.convertortemperatura;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConvertirTemp localTemp;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //////////////////////

        number = (EditText) findViewById(R.id.ingresar);

    }

    public void getFahrenheit(View v){
        int value = 0;
        try{
            value = Integer.parseInt(number.getText().toString());
            localTemp = new ConvertirTemp(value);
            Toast.makeText(getApplicationContext(), "Temperatura en Fahrenheit " + localTemp.getTemperature(), Toast.LENGTH_LONG).show();
            setOperations(localTemp.getTemperature());
            showRecords();
        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Error" +e, Toast.LENGTH_LONG).show();
        }
    }

    private void setOperations(int value){
        //Abrimos la base de datos 'Consultas' en modo escritura
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "Consultas", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos

                /*//Insertamos los datos en la tabla Usuarios
                try {
                    db.execSQL("INSERT INTO Consultas (valor) VALUES ("
                            + value + ")");
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(), "SQL exception " +e, Toast.LENGTH_LONG).show();
                }*/

                //Creamos el registro a insertar como objeto ContentValues
                ContentValues nuevoRegistro = new ContentValues();
                try {
                    //nuevoRegistro.put("valor", number.getText().toString());
                    nuevoRegistro.put("valor", value);


                    //Insertamos el registro en la base de datos (1er parámetro es tabla)
                    db.insert("Consultas", null, nuevoRegistro);
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(), "SQL exception " +e, Toast.LENGTH_LONG).show();
                }

        if(db != null){
            //Cerramos la base de datos
             db.close();
        }

        /*//Creamos el registro a insertar como objeto ContentValues
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("codigo", edT1.getText().toString());
        nuevoRegistro.put("nombre", edT2.getText().toString());

        //Insertamos el registro en la base de datos (1er parámetro es tabla)
        db.insert("Usuarios", null, nuevoRegistro);*/


        //Toast.makeText(getApplicationContext(), "Valores agregados", Toast.LENGTH_LONG).show();
    }

    public void showRecords(){
        TextView txtResultado = (TextView)findViewById(R.id.vistaConsultas);
        try {
            //Abrimos la base de datos 'DBUsuarios' en modo escritura
            UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "Consultas", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT valor FROM Consultas", null);
            c.moveToFirst();

            Toast.makeText(getApplicationContext(), "Registros " +c.getCount(), Toast.LENGTH_LONG).show();


            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    int valor =  c.getInt(0);
                    //String valor= c.getString(0);
                    txtResultado.append(" " + valor + "\n");
                } while(c.moveToNext());
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error al consultar",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
