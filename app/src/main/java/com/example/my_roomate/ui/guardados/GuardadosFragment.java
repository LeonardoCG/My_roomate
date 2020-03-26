package com.example.my_roomate.ui.guardados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.GuardadoAdapter;
import com.example.my_roomate.Propuesta;
import com.example.my_roomate.PropuestaAdapter;
import com.example.my_roomate.R;
import com.example.my_roomate.ui.guardados.GuardadosViewModel;

import java.security.Guard;
import java.util.ArrayList;
import java.util.List;

public class GuardadosFragment extends Fragment {

    private RecyclerView recycler_propuestas_guardadas;
    private RecyclerView.Adapter adapter_propuestas_guardadas;
    private GuardadosViewModel guardadosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inyectando datos fake
        int c1 = R.drawable.casa1;
        int c2 = R.drawable.casa2;
        int c3 = R.drawable.casa3;
        int userpic = R.drawable.user;
        List items_propuestas_guardadas = new ArrayList();
        items_propuestas_guardadas.add(new Propuesta("Alberto Jimenez","Mérida,Yuc","Casa Montejo","Busco rommie para compartir renta en casa Montejo","Mérida,Yuc","1200.00",4,true,c1, userpic));
        items_propuestas_guardadas.add(new Propuesta("Cecilia Aldair","Mérida,Yuc","Renta en Madero","Busco rommie para compartir renta en casa Madero","Mérida,Yuc","980.00",5,true, c2, userpic));
        items_propuestas_guardadas.add(new Propuesta("Sofia Melendez","Mérida,Yuc","Casa por Prepa2","Busco rommies urgentes para rentar casa cerca de la prepa2","Mérida,Yuc","1200.00",0,true, c3, userpic));

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
        adapter_propuestas_guardadas = new GuardadoAdapter(items_propuestas_guardadas);
        recycler_propuestas_guardadas.setAdapter(adapter_propuestas_guardadas);


        return root;
    }
}
