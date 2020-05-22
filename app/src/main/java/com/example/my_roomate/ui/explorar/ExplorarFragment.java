package com.example.my_roomate.ui.explorar;

import android.content.ContentValues;
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

import com.example.my_roomate.Propuesta;
import com.example.my_roomate.PropuestaAdapter;
import com.example.my_roomate.R;
import com.example.my_roomate.RecyclerAdapter;
import com.example.my_roomate.ui.explorar.ExplorarViewModel;
import com.example.my_roomate.Utils.utils;
import com.example.my_roomate.OpenHelper.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExplorarFragment extends Fragment implements RecyclerAdapter.onItemClickListenner {
    private ExplorarViewModel explorarViewModel;
    private RecyclerView recycler_propuestas_cercanas, recycler_mis_propuestas, recycler_propuestas_vistas;
    private RecyclerView.Adapter adapter_propuestas_cercanas, adapter_mis_propuestas, adapter_propuestas_vistas;
    SQLiteOpenHelper conn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inyectando datos fake
        int c1 = R.drawable.casa1;
        int c2 = R.drawable.casa2;
        int c3 = R.drawable.casa3;
        List items_propuestas_cercanas = new ArrayList();
        items_propuestas_cercanas.add(new Propuesta("Casa Montejo","Busco rommie para compartir renta en casa Montejo","Mérida,Yuc","1200.00",4,false,c1));
        items_propuestas_cercanas.add(new Propuesta("Renta en Madero","Busco rommie para compartir renta en casa Madero","Mérida,Yuc","980.00",5,false, c2));
        items_propuestas_cercanas.add(new Propuesta("Casa por Prepa2","Busco rommies urgentes para rentar casa cerca de la prepa2","Mérida,Yuc","1200.00",0,false, c3));
        List items_mis_propuestas = new ArrayList();
        items_mis_propuestas.add(new Propuesta("Casa en Centro","Casa para dos rommies en centro","Mérida,Yuc","1500.00",4,false,c1));
        items_mis_propuestas.add(new Propuesta("¿Compartimos casa?","Busco rommie para compartir renta cerca de Itzaes","Mérida,Yuc","900.00",5,false, c2));
        items_mis_propuestas.add(new Propuesta("Casa por TEC","Compartamos renta, casa por el tec poniente","Mérida,Yuc","800.00",0,false, c3));
        List items_propuestas_vistas = new ArrayList();
        items_propuestas_vistas.add(new Propuesta("Casa en Centro","Casa para dos rommies en centro","Mérida,Yuc","1500.00",4,false,c1));
        items_propuestas_vistas.add(new Propuesta("Casa Montejo","Busco rommie para compartir renta en casa Montejo","Mérida,Yuc","1200.00",4,false,c1));
        items_propuestas_vistas.add(new Propuesta("Casa por TEC","Compartamos renta, casa por el tec poniente","Mérida,Yuc","800.00",0,false, c3));

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
        adapter_propuestas_cercanas = new PropuestaAdapter(items_propuestas_cercanas);
        recycler_propuestas_cercanas.setAdapter(adapter_propuestas_cercanas);
        adapter_mis_propuestas = new PropuestaAdapter(items_mis_propuestas);
        recycler_mis_propuestas.setAdapter(adapter_mis_propuestas);
        adapter_propuestas_vistas = new PropuestaAdapter(items_propuestas_vistas);
        recycler_propuestas_vistas.setAdapter(adapter_propuestas_vistas);

        conn = new SQLiteOpenHelper(getContext(),utils.dbName,null,utils.db_version);

        return root;
    }

    @Override
    public void onItemClick(int index) {

    }

}
