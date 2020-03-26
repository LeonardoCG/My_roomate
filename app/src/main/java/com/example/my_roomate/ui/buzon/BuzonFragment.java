package com.example.my_roomate.ui.buzon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.Buzon;
import com.example.my_roomate.BuzonAdapter;
import com.example.my_roomate.R;


import java.util.ArrayList;
import java.util.List;

public class BuzonFragment extends Fragment {
    private RecyclerView recycler_buzon;
    private RecyclerView.Adapter adapter_buzon;
    private BuzonViewModel buzonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        int userpic = R.drawable.perfil;
        List items_buzon = new ArrayList();
        items_buzon.add(new Buzon("Alberto Jimenez","hola me interesa compartir.. habitacion contigo",12, userpic));
        items_buzon.add(new Buzon("Cecilia Aldair","hola me interesa compartir.. habitacion contigo",9, userpic));
        items_buzon.add(new Buzon("Sofia Melendez","hola me interesa compartir.. habitacion contigo",7 , userpic));

        buzonViewModel =
                ViewModelProviders.of(this).get(BuzonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buzon, container, false);

        //implementando recycler views
        //Obtener el recycler
        recycler_buzon = root.findViewById(R.id.recylcer_Buzon);
        recycler_buzon.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        recycler_buzon.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.VERTICAL, false));
        //crear un nuevo adapter
        adapter_buzon = new BuzonAdapter(items_buzon);
        recycler_buzon.setAdapter(adapter_buzon);


        return root;
    }
}
