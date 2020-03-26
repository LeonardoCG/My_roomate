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
public class CodigoFallido extends Fragment {
    private Button btn_error;
    View vista;

    public CodigoFallido() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_codigo_fallido, container, false);
        btn_error = root.findViewById(R.id.btn_fallido);

        btn_error.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Fragment fragment = new CodigoCel();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.check_codi_container, fragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

}
