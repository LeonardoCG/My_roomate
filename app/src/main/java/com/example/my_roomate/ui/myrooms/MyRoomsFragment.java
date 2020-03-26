package com.example.my_roomate.ui.myrooms;

import android.content.Intent;
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
import com.example.my_roomate.MyroomAdapter;
import com.example.my_roomate.Propuesta;
import com.example.my_roomate.R;
import com.example.my_roomate.menu_nav;
import com.example.my_roomate.ui.modal_poppup.CheckingCodigoCel;
import com.example.my_roomate.ui.myrooms.MyRoomsViewModel;
import com.example.my_roomate.ui.propuesta.PropuestaAdd;

import java.util.ArrayList;
import java.util.List;

public class MyRoomsFragment extends Fragment {

    private RecyclerView recycler_my_room;
    private RecyclerView.Adapter adapter_my_room;
    private MyRoomsViewModel myRoomsViewModel;
    private Button btn_add_propuesta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inyectando datos fake
        int c1 = R.drawable.casa1;
        int c2 = R.drawable.casa2;
        int c3 = R.drawable.casa3;
        //int userpic = R.drawable.user;
        List items_my_rooms = new ArrayList();
        items_my_rooms.add(new Propuesta("Casa 1","Busco rommie para compartir renta en casa Montejo","Mérida,Yuc","1200.00",4,true,c1));
        items_my_rooms.add(new Propuesta("Casa 2","Busco rommie para compartir renta en casa Madero","Mérida,Yuc","980.00",5,true, c2));
        items_my_rooms.add(new Propuesta("Casa 3","Busco rommies urgentes para rentar casa cerca de la prepa2","Mérida,Yuc","1200.00",0,true, c3));

        myRoomsViewModel =
                ViewModelProviders.of(this).get(MyRoomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_rooms, container, false);

        btn_add_propuesta = root.findViewById(R.id.add_propuesta);

        //implementando recycler views
        //Obtener el recycler
        recycler_my_room = root.findViewById(R.id.recycler_my_rooms);
        recycler_my_room.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        recycler_my_room.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.VERTICAL, false));
        //crear un nuevo adapter
        adapter_my_room = new MyroomAdapter(items_my_rooms);
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

        return root;
    }
}
