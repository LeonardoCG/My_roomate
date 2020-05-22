package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.content.ContentValues;

import com.example.my_roomate.OpenHelper.SQLiteOpenHelper;
import com.example.my_roomate.Utils.utils;

import static android.content.Context.MODE_PRIVATE;
import java.lang.*;

public class registro extends AppCompatActivity {

    private EditText et_nombre, et_correo, et_contraseña, et_confirmar;
    private String nombre, correo, contraseña, confcontraseña;
    SQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        setContentView(R.layout.activity_registro);

        //Setiar el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Activar la flecha de atras
        getSupportActionBar().setElevation(0); //quitar la elevación
        getSupportActionBar().setDisplayShowTitleEnabled(false); //quitar el titulo

        et_nombre = (EditText)findViewById(R.id.text_nombre);
        et_correo = (EditText)findViewById(R.id.text_correo);
        et_contraseña = (EditText)findViewById(R.id.text_contraseña);
        et_confirmar = (EditText)findViewById(R.id.text_contraseñaconf);
    }
    //Para regresar atras en el action bar
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
    }

    private void registrarUsuarios() {
        SQLiteOpenHelper conn= new  SQLiteOpenHelper (this,"roomate-app",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utils.names_user,et_nombre.getText().toString());
        values.put(utils.email_user,et_correo.getText().toString());
        values.put(utils.password_user,et_contraseña.getText().toString());
        values.put(utils.password_user,et_confirmar.getText().toString());

        Long res = db.insert(utils.table_user,utils.id_user,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+res,Toast.LENGTH_SHORT).show();
        db.close();

        inicialise ();
        if (!validate()) {
            Toast.makeText(this,"no se registro",Toast.LENGTH_SHORT).show();
        }
        else {
            successRegistro();
        }
    }
    public void successRegistro() {
        //cuando el registro es correcto te manda a explorar (es provosional)
        Intent intent = new Intent(this, CheckCodiContainer.class);
        startActivity(intent);
        //String msg_welcome = "Bienvenido " + nombre;
        //Toast.makeText(this, msg_welcome, Toast.LENGTH_LONG).show();
    }
    public boolean validate () {
        boolean valid = true;
        if (nombre.isEmpty()||nombre.length()>32) {
            et_nombre.setError("por favor escriba su nombre");
            valid = false;
        }
        if(correo.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            et_correo.setError("Porfavor escriba su correo");
            valid = false;
        }

        if(contraseña.isEmpty()){
            et_contraseña.setError("Porfavor escriba su contraseña");
            valid = false;
        }

        if(confcontraseña.isEmpty()){
            et_confirmar.setError("Porfavor escriba su contraseña");
            valid = false;
        }

        return valid;
    }
    public void inicialise (){
        nombre = et_nombre.getText().toString().trim();
        correo = et_correo.getText().toString().trim();
        contraseña = et_contraseña.getText().toString().trim();
        confcontraseña = et_confirmar.getText().toString().trim();
    }
}
