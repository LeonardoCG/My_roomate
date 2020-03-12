package com.example.my_roomate.ui.buzon;

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

import com.example.my_roomate.R;
import com.example.my_roomate.ui.buzon.BuzonViewModel;

public class BuzonFragment extends Fragment {
    private BuzonViewModel buzonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buzonViewModel =
                ViewModelProviders.of(this).get(BuzonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buzon, container, false);
        final TextView textView = root.findViewById(R.id.text_buzon);
        buzonViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
