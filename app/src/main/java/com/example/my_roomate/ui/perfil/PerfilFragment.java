package com.example.my_roomate.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_roomate.R;
import com.example.my_roomate.menu_nav;
import com.example.my_roomate.ui.configuracion.SettingsActivity;
import com.example.my_roomate.ui.perfil.PerfilViewModel;
import com.example.my_roomate.ui.propuesta.PropuestaAdd;

public class PerfilFragment extends Fragment {
    private PerfilViewModel perfilViewModel;
    private ImageButton btn_setting;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        //conectamos xml con class
        btn_setting = root.findViewById(R.id.settings_global);

        //Escuchadores
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
