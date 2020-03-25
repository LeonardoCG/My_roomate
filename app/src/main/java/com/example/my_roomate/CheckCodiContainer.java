package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.my_roomate.ui.modal_poppup.CodigoCel;

public class CheckCodiContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        setContentView(R.layout.activity_check_codi_container);
        //Setiar el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Activar la flecha de atras
        getSupportActionBar().setElevation(10); //ajustar la elevación

        //iniciamos el fragment
        Fragment fragment = new CodigoCel();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.check_codi_container, fragment).commit(); // en donde estoy , ¿que me vas a pintar?
    }
    //Para regresar atras en el action bar
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}
