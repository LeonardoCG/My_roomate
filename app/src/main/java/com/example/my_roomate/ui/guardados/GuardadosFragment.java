package com.example.my_roomate.ui.guardados;

import android.content.Context;
import android.content.Intent;
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
import com.example.my_roomate.ProposalDetail;
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

public class GuardadosFragment extends Fragment{

    private RecyclerView recycler_propuestas_guardadas;
    private PropuestaAdapter adapter_propuestas_guardadas;
    private GuardadosViewModel guardadosViewModel;
    private Retrofit retrofit;
    List<Proposel> likeProposel;
    SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        //Realizando la petición al webservice
        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Megajjks/dbroomate/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerPropuestas();
        //Instancias para acceder al método onclick de nuestros cardviews correspondientes a nuestros adapters
        adapter_propuestas_guardadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProposalDetail.class);
                intent.putExtra("tipocasa",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getType_house());
                intent.putExtra("titulo",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getTitle());
                intent.putExtra("lugar",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getLocalization());
                intent.putExtra("description",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getFull_description());
                intent.putExtra("camas",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getNumb_bed());
                intent.putExtra("banos",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getNumb_bathroom());
                intent.putExtra("salas",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getNumb_living_room());
                intent.putExtra("comedor",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getNumb_dinning_room());
                intent.putExtra("aire",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).isAir_conditional());
                intent.putExtra("boiler",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).isBoiler());
                intent.putExtra("internet",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).isInternet());
                intent.putExtra("terraza",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).isTerrace());
                intent.putExtra("Piscina",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).isPool());
                intent.putExtra("user",utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
                intent.putExtra("bio",utils.getShared_bio(preferences));
                intent.putExtra("in1",utils.getShared_interestings1(preferences));
                intent.putExtra("in2",utils.getShared_interestings2(preferences));
                intent.putExtra("in3",utils.getShared_interestings3(preferences));
                intent.putExtra("in4",utils.getShared_interestings4(preferences));
                intent.putExtra("in5",utils.getShared_interestings5(preferences));
                intent.putExtra("in6",utils.getShared_interestings6(preferences));
                intent.putExtra("imgcover",likeProposel.get(recycler_propuestas_guardadas.getChildAdapterPosition(v)).getImg_slide()[0]);
                intent.putExtra("imguser",utils.getShared_photo_profile(preferences));
                startActivity(intent);
            }
        });
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
