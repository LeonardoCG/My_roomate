package com.example.my_roomate.ui.perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

        getUser();

        return root;
    }

    private void getUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Megajjks/dbroomate/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //llamando a la interfaz
        JsonServer jsonServer = retrofit.create(JsonServer.class);
        //llamamos al método de la interfaz que vamos a usar
        Call<List<User>> call = jsonServer.getUsers();
        //creando la respuesta (si hubo error o fue exitoso)
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //si la respuesta llego pero hubo un problema como error de auth
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity().getBaseContext(),"codigo: " +response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                //sitodo salio bien pues traemos la respuesta
                List<User> listaUser = response.body();

                resposeUser = new ArrayList(); //Array donde se almacenaran mis usuarios que traiga en la api
                //acomodamos el contenido que vamos a traer en el response
                for (User user: listaUser ){
                    if(String.valueOf(utils.getSharedUid(preferences)).equals(user.getId_user())){
                        resposeUser.add(new User(user.getId_user(),user.getEmail(),user.getPassword()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
