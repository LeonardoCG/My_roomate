package com.example.my_roomate.ui.modal_poppup;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.my_roomate.R;
import com.example.my_roomate.menu_nav;
import com.example.my_roomate.registro;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckingCodigoCel extends Fragment {

    private Button btn_comprobar_codigo;

    public CheckingCodigoCel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_checking_codigo_cel, container, false);
        //Enlazando los componentes del fragment con su clase
        btn_comprobar_codigo = root.findViewById(R.id.btn_verificar_codigo_cel);

        btn_comprobar_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), menu_nav.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
