package com.example.my_roomate.ui.modal_poppup;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_roomate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodigoCel extends Fragment {

    private Button btn_enviar_codigo;

    public CodigoCel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_codigo_cel, container, false);

        //Enlazamos los componentes de nuestra vista del fragment con su clase
        btn_enviar_codigo = root.findViewById(R.id.btn_enviar_codigo_cel);

        btn_enviar_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CheckingCodigoCel();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.check_codi_container, fragment).commit();
            }
        });

        return root;
    }

}
