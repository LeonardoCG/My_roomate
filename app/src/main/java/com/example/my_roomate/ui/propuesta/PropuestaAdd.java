package com.example.my_roomate.ui.propuesta;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_roomate.R;
import com.example.my_roomate.ui.guardados.GuardadosFragment;
import com.example.my_roomate.ui.modal_poppup.CheckingCodigoCel;
import com.example.my_roomate.ui.myrooms.MyRoomsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropuestaAdd extends Fragment {

    private Button btn_post;

    public PropuestaAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_propuesta_add, container, false);

        btn_post = root.findViewById(R.id.btn_post_propuesta);

        //Setiar el action bar en el fragment

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Datos Guardados", Toast.LENGTH_LONG).show();
                Fragment fragment = new MyRoomsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment).commit();
            }
        });

        return root;
    }

}
