package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_roomate.OpenHelper.SQLiteOpenHelper;
import com.example.my_roomate.Utils.utils;

public class Login extends AppCompatActivity {

    private Button btn_continuar, btn_facebook;
    private EditText input_email, input_password;
    private TextView forget_password, registrarme;
    SQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        //Para desaparecer el Toolbar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        //Configurar los parametros para realizar consultas a la bd
        conn = new SQLiteOpenHelper(this,utils.dbName,null,utils.db_version);

        //vinculando recursos del layout con la clase
        btn_continuar = findViewById(R.id.btn_continuar_login);
        btn_facebook = findViewById(R.id.btn_facebook_login);
        input_email = findViewById(R.id.input_email_login);
        input_password = findViewById(R.id.input_password_login);
        forget_password = findViewById(R.id.forget_pass);
        registrarme = findViewById(R.id.txt_registrarse_login);



        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_email.getText().toString().isEmpty() || input_password.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Datos vacios, porfavor introduce tu email y password", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });

        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Proximamente conexión a facebook", Toast.LENGTH_LONG).show();
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "recuperar contraseña", Toast.LENGTH_LONG).show();
            }
        });

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), registro.class);
                startActivity(intent);
            }
        });

    }
    private void login(){
        SQLiteDatabase db = conn.getReadableDatabase();
        //Se crear un arreglo asigando los valores del input email y password
        String[] args = new String[]{
                input_email.getText().toString(),
                input_password.getText().toString()};
        //hago una consulta con los datos a la tabla user
        String query = String.format("SELECT id_user FROM %s WHERE %s LIKE ? AND %s LIKE ?",
                utils.table_user,utils.email_user,utils.password_user);
        Cursor c = db.rawQuery(query,args);
        c.moveToFirst();
        if(c.getCount()>0) {
            savePreferene(c.getInt(0));
            c.close();
            //EventActivity();
            succesCheck();
        }
        else
            Toast.makeText(getApplicationContext(),"No se encontraron coincidencias",Toast.LENGTH_SHORT).show();
        c.close();
    }

    //Intent que me llevara al explore en caso de que mis credenciales sean validadas de forma correcta
    private void succesCheck(){
        Intent intent = new Intent(getBaseContext(), menu_nav.class); // donde estoy , donde quiero ir
        startActivity(intent);
    }

    private void savePreferene(int id){
        SharedPreferences preferences = getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(utils.shared_id_user,id);
        edit.commit();
    }
}
