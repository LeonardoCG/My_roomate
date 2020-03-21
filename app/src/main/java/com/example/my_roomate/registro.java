package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;
import java.lang.*;

public class registro extends AppCompatActivity {

    private EditText et_nombre, et_correo, et_contraseña, et_confirmar;
    private String nombre, correo, contraseña, confcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        setContentView(R.layout.activity_registro);
        //espero que desaparezca el toolbar sin el cuadro feo jajaja
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        et_nombre = (EditText)findViewById(R.id.text_nombre);
        et_correo = (EditText)findViewById(R.id.text_correo);
        et_contraseña = (EditText)findViewById(R.id.text_contraseña);
        et_confirmar = (EditText)findViewById(R.id.text_contraseñaconf);
    }
    //Metodo para el boton de registrarse
    public void Guardar(View view){
        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        String confirmar = et_confirmar.getText().toString();

        SharedPreferences preferencias= getSharedPreferences("registro",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(nombre, correo);
        obj_editor.putString(nombre, contraseña);
        obj_editor.putString(nombre,confirmar);
        obj_editor.commit();

        //Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_LONG).show();

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
        Intent intent = new Intent(this, menu_nav.class);
        startActivity(intent);
        String msg_welcome = "Bienvenido " + nombre;
        Toast.makeText(this, msg_welcome, Toast.LENGTH_LONG).show();
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
