package com.example.my_roomate.ui.explorar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.Interface.JsonServer;
import com.example.my_roomate.Model.Proposel;
import com.example.my_roomate.Model.User;
import com.example.my_roomate.ProposalDetail;
import com.example.my_roomate.Propuesta;
import com.example.my_roomate.PropuestaAdapter;
import com.example.my_roomate.R;
import com.example.my_roomate.RecyclerAdapter;
import com.example.my_roomate.registro;
import com.example.my_roomate.ui.explorar.ExplorarViewModel;
import com.example.my_roomate.Utils.utils;
import com.example.my_roomate.OpenHelper.SQLiteOpenHelper;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExplorarFragment extends Fragment implements RecyclerAdapter.onItemClickListenner {
    private ExplorarViewModel explorarViewModel;
    private RecyclerView recycler_propuestas_cercanas, recycler_mis_propuestas, recycler_propuestas_vistas;
    private PropuestaAdapter adapter_propuestas_cercanas, adapter_mis_propuestas, adapter_propuestas_vistas;
    SQLiteOpenHelper conn;
    SharedPreferences preferences;
    List<Proposel> listaUser, myProposel, closeProposel;
    private Retrofit retrofit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //asignando los datos guardadados globalmente
        preferences = this.getActivity().getSharedPreferences(utils.SHARED_FILE, Context.MODE_PRIVATE);

        explorarViewModel =
                ViewModelProviders.of(this).get(ExplorarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_explorar, container, false);
        //implementando recycler views
        //Obtener el recycler
        recycler_propuestas_cercanas = root.findViewById(R.id.recylcer_propuestas_cercanas);
        recycler_propuestas_cercanas.setHasFixedSize(true); //ocupe espacios el recylcer
        recycler_mis_propuestas = root.findViewById(R.id.recylcer_mis_propuestas);
        recycler_mis_propuestas.setHasFixedSize(true);
        recycler_propuestas_vistas = root.findViewById(R.id.recylcer_propuestas_vistas);
        recycler_propuestas_vistas.setHasFixedSize(true);

        //Usar el administrador para LinearLayout
        recycler_propuestas_cercanas.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_mis_propuestas.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_propuestas_vistas.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        //crear un nuevo adapter
        adapter_propuestas_cercanas = new PropuestaAdapter(getActivity().getApplicationContext());
        recycler_propuestas_cercanas.setAdapter(adapter_propuestas_cercanas);
        adapter_mis_propuestas = new PropuestaAdapter(getActivity().getApplicationContext());
        recycler_mis_propuestas.setAdapter(adapter_mis_propuestas);
        adapter_propuestas_vistas = new PropuestaAdapter(getActivity().getApplicationContext());
        recycler_propuestas_vistas.setAdapter(adapter_propuestas_vistas);

        //Instancias para acceder al método onclick de nuestros cardviews correspondientes a nuestros adapters
        adapter_propuestas_cercanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProposalDetail.class);
                intent.putExtra("tipocasa",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getType_house());
                intent.putExtra("titulo",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getTitle());
                intent.putExtra("lugar",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getLocalization());
                intent.putExtra("description",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getFull_description());
                intent.putExtra("camas",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bed());
                intent.putExtra("banos",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bathroom());
                intent.putExtra("salas",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_living_room());
                intent.putExtra("comedor",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_dinning_room());
                intent.putExtra("aire",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isAir_conditional());
                intent.putExtra("boiler",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isBoiler());
                intent.putExtra("internet",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isInternet());
                intent.putExtra("terraza",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isTerrace());
                intent.putExtra("Piscina",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isPool());
                intent.putExtra("user",utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
                intent.putExtra("bio",utils.getShared_bio(preferences));
                intent.putExtra("in1",utils.getShared_interestings1(preferences));
                intent.putExtra("in2",utils.getShared_interestings2(preferences));
                intent.putExtra("in3",utils.getShared_interestings3(preferences));
                intent.putExtra("in4",utils.getShared_interestings4(preferences));
                intent.putExtra("in5",utils.getShared_interestings5(preferences));
                intent.putExtra("in6",utils.getShared_interestings6(preferences));
                intent.putExtra("imgcover",closeProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getImg_slide()[0]);
                intent.putExtra("imguser",utils.getShared_photo_profile(preferences));
                startActivity(intent);
            }
        });

        adapter_mis_propuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProposalDetail.class);
                intent.putExtra("tipocasa",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getType_house());
                intent.putExtra("titulo",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getTitle());
                intent.putExtra("lugar",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getLocalization());
                intent.putExtra("description",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getFull_description());
                intent.putExtra("camas",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bed());
                intent.putExtra("banos",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bathroom());
                intent.putExtra("salas",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_living_room());
                intent.putExtra("comedor",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_dinning_room());
                intent.putExtra("aire",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isAir_conditional());
                intent.putExtra("boiler",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isBoiler());
                intent.putExtra("internet",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isInternet());
                intent.putExtra("terraza",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isTerrace());
                intent.putExtra("Piscina",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isPool());
                intent.putExtra("user",utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
                intent.putExtra("bio",utils.getShared_bio(preferences));
                intent.putExtra("in1",utils.getShared_interestings1(preferences));
                intent.putExtra("in2",utils.getShared_interestings2(preferences));
                intent.putExtra("in3",utils.getShared_interestings3(preferences));
                intent.putExtra("in4",utils.getShared_interestings4(preferences));
                intent.putExtra("in5",utils.getShared_interestings5(preferences));
                intent.putExtra("in6",utils.getShared_interestings6(preferences));
                intent.putExtra("imgcover",myProposel.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getImg_slide()[0]);
                intent.putExtra("imguser",utils.getShared_photo_profile(preferences));
                startActivity(intent);
            }
        });

        adapter_propuestas_vistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProposalDetail.class);
                intent.putExtra("tipocasa",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getType_house());
                intent.putExtra("titulo",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getTitle());
                intent.putExtra("lugar",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getLocalization());
                intent.putExtra("description",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getFull_description());
                intent.putExtra("camas",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bed());
                intent.putExtra("banos",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_bathroom());
                intent.putExtra("salas",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_living_room());
                intent.putExtra("comedor",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getNumb_dinning_room());
                intent.putExtra("aire",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isAir_conditional());
                intent.putExtra("boiler",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isBoiler());
                intent.putExtra("internet",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isInternet());
                intent.putExtra("terraza",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isTerrace());
                intent.putExtra("Piscina",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).isPool());
                intent.putExtra("user",utils.getShared_names(preferences) + " " + utils.getShared_last_name(preferences));
                intent.putExtra("bio",utils.getShared_bio(preferences));
                intent.putExtra("in1",utils.getShared_interestings1(preferences));
                intent.putExtra("in2",utils.getShared_interestings2(preferences));
                intent.putExtra("in3",utils.getShared_interestings3(preferences));
                intent.putExtra("in4",utils.getShared_interestings4(preferences));
                intent.putExtra("in5",utils.getShared_interestings5(preferences));
                intent.putExtra("in6",utils.getShared_interestings6(preferences));
                intent.putExtra("imgcover",listaUser.get(recycler_mis_propuestas.getChildAdapterPosition(v)).getImg_slide()[0]);
                intent.putExtra("imguser",utils.getShared_photo_profile(preferences));
                startActivity(intent);
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Megajjks/dbroomate/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerPropuestas();

        conn = new SQLiteOpenHelper(getContext(),utils.dbName,null,utils.db_version);
        loadSharedPreferences();
        return root;
    }

    @Override
    public void onItemClick(int index) {

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
                listaUser = response.body();
                myProposel = new ArrayList();
                closeProposel = new ArrayList<>();
                for(Proposel proposel: listaUser){
                    if(proposel.getId_user_create().equals(String.valueOf(utils.getSharedUid(preferences)))){
                        myProposel.add(proposel);
                    }else{
                        closeProposel.add(proposel);
                    }
                }

                adapter_propuestas_cercanas.adicionarListaPropuesta(closeProposel);
                adapter_mis_propuestas.adicionarListaPropuesta(myProposel);
                adapter_propuestas_vistas.adicionarListaPropuesta(listaUser);
            }

            @Override
            public void onFailure(Call<List<Proposel>> call, Throwable t) {
                Toast.makeText(getActivity().getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadSharedPreferences(){
        //int uid = utils.getSharedUid(preferences);
        String uid = utils.getShared_names(preferences);
        Toast.makeText(getContext(),"Usuario id: " + uid,Toast.LENGTH_LONG).show();
    }
}
