package com.example.my_roomate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuardadoAdapter extends RecyclerView.Adapter<GuardadoAdapter.ViewHolderGuardado> {

    private List<Propuesta> items;

    //Esta clase es una referencia al layout  recycler_template la plantilla
    public class ViewHolderGuardado extends RecyclerView.ViewHolder {

        //campos respectivos al CardView
        public TextView txt_head, txt_body, ubicacion, precio, username, usernameubicacion;
        public ImageView cover, userpicture;

        public ViewHolderGuardado(@NonNull View v) {
            super(v);
            userpicture = v.findViewById(R.id.userpicture_cv2);
            username = v.findViewById(R.id.username_cv2);
            usernameubicacion = v.findViewById(R.id.usernamelocalization_cv2);
            txt_head = v.findViewById(R.id.text_head_cv2);
            txt_body = v.findViewById(R.id.text_body_cv2);
            ubicacion = v.findViewById(R.id.text_lugar_cv2);
            precio = v.findViewById(R.id.text_precio_cv2);
            cover = v.findViewById(R.id.cover_card_cv2);
        }
    }

    //Funcion que me dara acceso al arreglo
    public GuardadoAdapter(List<Propuesta> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolderGuardado onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.guardado_card_view, viewGroup, false);
        return new ViewHolderGuardado(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGuardado viewHolder, int i) {
        viewHolder.userpicture.setImageResource(items.get(i).get_userpicture());
        viewHolder.username.setText(items.get(i).get_username());
        viewHolder.usernameubicacion.setText(items.get(i).get_usernmamelocalization());
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
