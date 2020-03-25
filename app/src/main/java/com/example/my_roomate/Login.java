package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button btn_continuar, btn_facebook;
    private EditText input_email, input_password;
    private TextView forget_password, registrarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        //Para desaparecer el Toolbar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

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
                Intent intent = new Intent(getBaseContext(), menu_nav.class); // donde estoy , donde quiero ir
                startActivity(intent);
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
}
