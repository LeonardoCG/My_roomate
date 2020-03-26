package com.example.my_roomate.ui.modal_poppup;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

import com.example.my_roomate.R;
import com.example.my_roomate.menu_nav;
/**
 * A simple {@link Fragment} subclass.
 */
public class CodigoComprobado extends Fragment {
    private Button btn_confirmar;
    View vista;


    public CodigoComprobado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_codigo_comprobado, container, false);
        btn_confirmar = root.findViewById(R.id.btn_confirmado);

        btn_confirmar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), menu_nav.class);
                startActivity(intent);
            }
        });
        return root;
        // Inflate the layout for this fragment
    }

}
