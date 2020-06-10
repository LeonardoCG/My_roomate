package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.my_roomate.Utils.utils;

public class ProposalDetail extends AppCompatActivity {

    private ImageView img_proposel, img_user;
    private TextView tipo_casa,titulo,text_lugar,full_description,num_camas,num_banos,num_salas,num_comedor,aircondicionado,boiler,internet,terraza,piscina,username,bio_user,i1,i2,i3,i4,i5,i6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_detail);
        setTheme(R.style.AppTheme);//Ajustamos para que este sea el tema a cargar
        //Para desaparecer el Toolbar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        tipo_casa = findViewById(R.id.tipo_casa_pd);
        titulo = findViewById(R.id.titulo_pd);
        text_lugar = findViewById(R.id.text_lugar_pd);
        full_description = findViewById(R.id.full_description_pd);
        num_camas = findViewById(R.id.num_camas_pd);
        num_banos = findViewById(R.id.num_banos_pd);
        num_salas = findViewById(R.id.num_salas_pd);
        num_comedor = findViewById(R.id.num_comedor_pd);
        aircondicionado = findViewById(R.id.aircondicionado_pd);
        boiler = findViewById(R.id.boiler_pd);
        internet = findViewById(R.id.internet_pd);
        terraza = findViewById(R.id.terraza_pd);
        piscina = findViewById(R.id.piscina_pd);
        username = findViewById(R.id.username_pd);
        bio_user = findViewById(R.id.bio_user_pd);
        i1 = findViewById(R.id.interes1_pd);
        i2 = findViewById(R.id.interes2_pd);
        i3 = findViewById(R.id.interes3_pd);
        i4 = findViewById(R.id.interes4_pd);
        i5 = findViewById(R.id.interes5_pd);
        i6 = findViewById(R.id.interes6_pd);
        img_proposel = findViewById(R.id.img_proposel_detail);
        img_user = findViewById(R.id.img_user_pd);

        //Cambiando los datos por los que son pasados por el intent
        tipo_casa.setText(getIntent().getExtras().getString("tipocasa"));
        titulo.setText(getIntent().getExtras().getString("titulo"));
        text_lugar.setText(getIntent().getExtras().getString("lugar"));
        full_description.setText(getIntent().getExtras().getString("description"));
        num_camas.setText(getIntent().getExtras().getString("camas"));
        num_banos.setText(getIntent().getExtras().getString("banos"));
        num_salas.setText(getIntent().getExtras().getString("salas"));
        num_comedor.setText(getIntent().getExtras().getString("comedor"));
        if(!(getIntent().getExtras().getBoolean("aire"))){
            aircondicionado.setText(" ");
        }
        if(!(getIntent().getExtras().getBoolean("boiler"))){
            boiler.setText(" ");
        }
        if(!(getIntent().getExtras().getBoolean("internet"))){
            internet.setText(" ");
        }
        if(!(getIntent().getExtras().getBoolean("terraza"))){
            terraza.setText(" ");
        }
        if(!(getIntent().getExtras().getBoolean("Piscina"))){
            piscina.setText(" ");
        }
        username.setText(getIntent().getExtras().getString("user"));
        bio_user.setText(getIntent().getExtras().getString("bio"));
        i1.setText(getIntent().getExtras().getString("in1"));
        i2.setText(getIntent().getExtras().getString("in2"));
        i3.setText(getIntent().getExtras().getString("in3"));
        i4.setText(getIntent().getExtras().getString("in4"));
        i5.setText(getIntent().getExtras().getString("in5"));
        i6.setText(getIntent().getExtras().getString("in6"));
        //trayendo las imagenes con Glide
        //foto de perfil del user
        Glide.with(this)
                .load(getIntent().getExtras().getString("imguser"))
                .placeholder(R.drawable.ic_account_circle_blue_grey_50_24dp)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_user);
        //foto de la propuesta
        Glide.with(this)
                .load(getIntent().getExtras().getString("imgcover"))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholderimg)
                .into(img_proposel);
    }
}
