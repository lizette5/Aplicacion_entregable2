package com.example.aplicacion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Utilidades.Utilidades;

public class Registro extends AppCompatActivity {
    EditText campoNombre,campoContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoNombre=(EditText)findViewById(R.id.campo_nombre);
        campoContraseña=(EditText)findViewById(R.id.campo_contraseña);
    }
    public void Regis(View view){

        registrarUsuarios();
    }

    private void registrarUsuarios(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASEÑA,campoContraseña.getText().toString());

        Long nombreResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_NOMBRE,values);
        Toast.makeText(getApplicationContext(),"Registrado"+nombreResultante,Toast.LENGTH_SHORT).show();
    }
}