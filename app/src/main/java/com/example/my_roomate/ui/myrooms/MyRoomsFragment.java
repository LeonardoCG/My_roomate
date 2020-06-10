package com.example.my_roomate.ui.myrooms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.GuardadoAdapter;
import com.example.my_roomate.Interface.JsonServer;
import com.example.my_roomate.Model.Proposel;
import com.example.my_roomate.MyroomAdapter;
import com.example.my_roomate.ProposalDetail;
import com.example.my_roomate.Propuesta;
import com.example.my_roomate.R;
import com.example.my_roomate.Utils.utils;
import com.example.my_roomate.menu_nav;
import com.example.my_roomate.ui.modal_poppup.CheckingCodigoCel;
import com.example.my_roomate.ui.myrooms.MyRoomsViewModel;
import com.example.my_roomate.ui.propuesta.PropuestaAdd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRoomsFragment extends Fragment {

    private RecyclerView recycler_my_room;
    private MyroomAdapter adapter_my_room;
    private MyRoomsViewModel myRoomsViewModel;
    private Button btn_add_propuesta;
    SharedPreferences preferences;
    List<Proposel> likeProposel;
    private Retrofit retrofit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRoomsViewModel =
                ViewModelProviders.of(this).get(MyRoomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_rooms, container, false);
        //asignando los datos guardadados globalmente
        preferences = this.getActivity().getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);
        btn_add_propuesta = root.findViewById(R.id.add_propuesta);

        //implementando recycler views
        //Obtener el recycler
        recycler_my_room = root.findViewById(R.id.recycler_my_rooms);
        recycler_my_room.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        recycler_my_room.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.VERTICAL, false));
        //crear un nuevo adapter
        adapter_my_room = new MyroomAdapter(getActivity().getApplicationContext());
        recycler_my_room.setAdapter(adapter_my_room);

        //Escuchadores
        btn_add_propuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PropuestaAdd();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment).commit();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Megajjks/dbroomate/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerPropuestas();

        //Instancias para acceder al método onclick de nuestros cardviews correspondientes a nuestros adapters
        adapter_my_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProposalDetail.class);
                intent.putExtra("tipocasa",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getType_house());
                intent.putExtra("titulo",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getTitle());
                intent.putExtra("lugar",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getLocalization());
                intent.putExtra("description",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getFull_description());
                intent.putExtra("camas",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getNumb_bed());
                intent.putExtra("banos",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getNumb_bathroom());
                intent.putExtra("salas",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getNumb_living_room());
                intent.putExtra("comedor",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getNumb_dinning_room());
                intent.putExtra("aire",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).isAir_conditional());
                intent.putExtra("boiler",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).isBoiler());
                intent.putExtra("internet",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).isInternet());
                intent.putExtra("terraza",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).isTerrace());
                intent.putExtra("Piscina",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).isPool());
                intent.putExtra("user",utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
                intent.putExtra("bio",utils.getShared_bio(preferences));
                intent.putExtra("in1",utils.getShared_interestings1(preferences));
                intent.putExtra("in2",utils.getShared_interestings2(preferences));
                intent.putExtra("in3",utils.getShared_interestings3(preferences));
                intent.putExtra("in4",utils.getShared_interestings4(preferences));
                intent.putExtra("in5",utils.getShared_interestings5(preferences));
                intent.putExtra("in6",utils.getShared_interestings6(preferences));
                intent.putExtra("imgcover",likeProposel.get(recycler_my_room.getChildAdapterPosition(v)).getImg_slide()[0]);
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
                    if(proposel.getId_user_create().equals(String.valueOf(utils.getSharedUid(preferences)))){
                        likeProposel.add(proposel);
                    }
                }
                adapter_my_room.adicionarListaPropuesta(likeProposel);
            }

            @Override
            public void onFailure(Call<List<Proposel>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
