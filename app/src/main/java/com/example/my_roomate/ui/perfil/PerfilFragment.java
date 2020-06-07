package com.example.my_roomate.ui.perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.my_roomate.Interface.JsonServer;
import com.example.my_roomate.Model.User;
import com.example.my_roomate.R;
import com.example.my_roomate.Utils.utils;
import com.example.my_roomate.menu_nav;
import com.example.my_roomate.ui.configuracion.SettingsActivity;
import com.example.my_roomate.ui.perfil.PerfilViewModel;
import com.example.my_roomate.ui.propuesta.PropuestaAdd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilFragment extends Fragment {
    private PerfilViewModel perfilViewModel;
    private ImageButton btn_setting;
    private List<User> resposeUser;
    private ImageView img_user;
    private TextView name_user,ubication_user,bio_user,phone,address,inte1,inte2,inte3,inte4,inte5,inte6;
    SharedPreferences preferences;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        //asignando los datos guardadados globalmente
        preferences = this.getActivity().getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);
        //conectamos xml con class
        btn_setting = root.findViewById(R.id.settings_global);

        //Escuchadores
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        //asignando los datos guardadados globalmente
        preferences = this.getActivity().getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);
        //relacionamos la clase con la vista
        name_user = root.findViewById(R.id.name_user_profile);
        ubication_user = root.findViewById(R.id.ubication_user_profile);
        bio_user = root.findViewById(R.id.bio_user_profile);
        phone = root.findViewById(R.id.txt_phone_profile);
        address = root.findViewById(R.id.txt_ubication_profile);
        inte1 = root.findViewById(R.id.interes1);
        inte2 = root.findViewById(R.id.interes2);
        inte3 = root.findViewById(R.id.interes3);
        inte4 = root.findViewById(R.id.interes4);
        inte5 = root.findViewById(R.id.interes5);
        inte6 = root.findViewById(R.id.interes6);
        img_user = root.findViewById(R.id.img_user_profile);
        //Se pintan los datos a la vista
        name_user.setText(utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
        ubication_user.setText(utils.getShared_ubication(preferences));
        bio_user.setText(utils.getShared_bio(preferences));
        phone.setText(utils.getShared_phone(preferences));
        address.setText(utils.getShared_address(preferences));
        inte1.setText(utils.getShared_interestings1(preferences));
        inte2.setText(utils.getShared_interestings2(preferences));
        inte3.setText(utils.getShared_interestings3(preferences));
        inte4.setText(utils.getShared_interestings4(preferences));
        inte5.setText(utils.getShared_interestings5(preferences));
        inte6.setText(utils.getShared_interestings6(preferences));
        //se realiza la consulta a la api para obtener la imagen del perfil
        Glide.with(getActivity().getApplicationContext())
                .load(utils.getShared_photo_profile(preferences))
                .placeholder(R.drawable.ic_account_circle_blue_grey_50_24dp)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_user);
        return root;
    }
}
