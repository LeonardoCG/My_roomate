package com.example.my_roomate.ui.explorar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_roomate.R;
import com.example.my_roomate.RecyclerAdapter;
import com.example.my_roomate.ui.explorar.ExplorarViewModel;

import java.util.ArrayList;

public class ExplorarFragment extends Fragment implements RecyclerAdapter.onItemClickListenner {
    private ExplorarViewModel explorarViewModel;
        ArrayList<String> data;
        RecyclerView recycler;
        Toast mToast;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        explorarViewModel =
                ViewModelProviders.of(this).get(ExplorarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_explorar, container, false);
        //implementando recycler views
        recycler = root.findViewById(R.id.my_recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager( root.getContext(), LinearLayoutManager.VERTICAL, false));
        data = new ArrayList<String>();
        for (int i =0; i < 50; i++){
            data.add("numero" + i);
            RecyclerAdapter adapter = new RecyclerAdapter(data, this);
            recycler.setAdapter(adapter);
        }


        return root;
    }

    @Override
    public void onItemClick(int index) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getContext(), data.get(index), Toast.LENGTH_LONG);
    }
}
