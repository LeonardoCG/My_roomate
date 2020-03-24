package com.example.my_roomate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyroomAdapter extends RecyclerView.Adapter<MyroomAdapter.ViewHolderMyroom> {

    private List<Propuesta> items;

    public class ViewHolderMyroom extends RecyclerView.ViewHolder {

        public TextView txt_head, txt_body, ubicacion, precio;
        public ImageView cover;

        public ViewHolderMyroom(@NonNull View v) {
            super(v);
            txt_head = v.findViewById(R.id.text_head_cv3);
            txt_body = v.findViewById(R.id.text_body_cv3);
            ubicacion = v.findViewById(R.id.text_lugar_cv3);
            precio = v.findViewById(R.id.text_precio_cv3);
            cover = v.findViewById(R.id.cover_card);
        }
    }

    //Funcion que me dara acceso al arreglo
    public MyroomAdapter(List<Propuesta> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolderMyroom onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_rooms_card_view, viewGroup, false);
        return new MyroomAdapter.ViewHolderMyroom(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMyroom viewHolder, int i) {
        viewHolder.txt_head.setText(items.get(i).get_title());
        viewHolder.txt_body.setText(items.get(i).get_description());
        viewHolder.ubicacion.setText(items.get(i).get_localization());
        viewHolder.precio.setText(items.get(i).get_cost());
        viewHolder.cover.setImageResource(items.get(i).get_img());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
