package com.example.my_roomate.ui.guardados;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.GuardadoAdapter;
import com.example.my_roomate.Interface.JsonServer;
import com.example.my_roomate.Model.Proposel;
import com.example.my_roomate.Propuesta;
import com.example.my_roomate.PropuestaAdapter;
import com.example.my_roomate.R;
import com.example.my_roomate.Utils.utils;
import com.example.my_roomate.ui.guardados.GuardadosViewModel;

import java.security.Guard;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuardadosFragment extends Fragment {

    private RecyclerView recycler_propuestas_guardadas;
    private PropuestaAdapter adapter_propuestas_guardadas;
    private GuardadosViewModel guardadosViewModel;
    private Retrofit retrofit;
    SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        //Inyectando datos fake
        int c1 = R.drawable.casa1;
        int c2 = R.drawable.casa2;
        int c3 = R.drawable.casa3;
        int userpic = R.drawable.user;
        List items_propuestas_guardadas = new ArrayList();
        items_propuestas_guardadas.add(new Propuesta("Alberto Jimenez","Mérida,Yuc","Casa Montejo","Busco rommie para compartir renta en casa Montejo","Mérida,Yuc","1200.00",4,true,c1, userpic));
        items_propuestas_guardadas.add(new Propuesta("Cecilia Aldair","Mérida,Yuc","Renta en Madero","Busco rommie para compartir renta en casa Madero","Mérida,Yuc","980.00",5,true, c2, userpic));
        items_propuestas_guardadas.add(new Propuesta("Sofia Melendez","Mérida,Yuc","Casa por Prepa2","Busco rommies urgentes para rentar casa cerca de la prepa2","Mérida,Yuc","1200.00",0,true, c3, userpic));

         */

        //asignando los datos guardadados globalmente
        preferences = this.getActivity().getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);

        guardadosViewModel =
                ViewModelProviders.of(this).get(GuardadosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_guardados, container, false);

        //implementando recycler views
        //Obtener el recycler
        recycler_propuestas_guardadas = root.findViewById(R.id.recylcer_propuestas_guardadas);
        recycler_propuestas_guardadas.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        recycler_propuestas_guardadas.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.VERTICAL, false));
        //crear un nuevo adapter
        adapter_propuestas_guardadas = new PropuestaAdapter(getActivity().getApplicationContext());
        recycler_propuestas_guardadas.setAdapter(adapter_propuestas_guardadas);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Megajjks/dbroomate/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerPropuestas();

        return root;
    }

    public void obtenerPropuestas(){
        //llamando a la interfaz
        JsonServer jsonServer = retrofit.create(JsonServer.class);
        //llamamos al método de la interfaz que vamos a usar
        Call<List<Proposel>> call = jsonServer.getPropuestas();
        //creando la respuesta (si hubo error o fue exitoso)
        call.enqueue(new Callback<List<Proposel>>() {
            @Override
            public void onResponse(Call<List<Proposel>> call, Response<List<Proposel>> response) {
                //si la respuesta llego pero hubo un problema como error de auth
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity().getBaseContext(),"codigo: " +response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Proposel> listaUser = response.body();
                List<Proposel> likeProposel;
                likeProposel = new ArrayList();
                for(Proposel proposel: listaUser){
                    if(proposel.getId_user_favorite().equals(String.valueOf(utils.getSharedUid(preferences)))){
                        likeProposel.add(proposel);
                    }
                }
                adapter_propuestas_guardadas.adicionarListaPropuesta(likeProposel);
            }

            @Override
            public void onFailure(Call<List<Proposel>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
