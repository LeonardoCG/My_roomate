package com.example.my_roomate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuzonAdapter extends RecyclerView.Adapter<BuzonAdapter.ViewHolderBuzon> {
    private List<Buzon> items;

    //Esta clase es una referencia al layout  recycler_template la plantilla
    public class ViewHolderBuzon extends RecyclerView.ViewHolder {

        //campos respectivos al CardView
        public TextView txt_head, txt_body, txt_time;


        public ViewHolderBuzon(View v) {
            super(v);
            txt_head = v.findViewById(R.id.usuario);
            txt_body = v.findViewById(R.id.descripcion);
            txt_time = v.findViewById(R.id.hora);

        }
    }

    //Funcion que me dara acceso al arreglo
    public BuzonAdapter(List<Buzon> items){
        this.items = items;
    }

    //ViewGroup es una colección de recursos, son las secciones de los espacios disponibles que estan en el recycler view
    //Aquí
    @NonNull
    @Override
    public BuzonAdapter.ViewHolderBuzon onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.buzon_card_view, viewGroup, false);
        return new BuzonAdapter.ViewHolderBuzon(v);
    }


    // recibe dos parametros, nuestro item card o template y la posición donde quiero que se pinte
    @Override
    public void onBindViewHolder(@NonNull BuzonAdapter.ViewHolderBuzon viewHolder, int i) {
        viewHolder.txt_head.setText(items.get(i).get_usuario());
        viewHolder.txt_body.setText(items.get(i).get_descripcion());
        String time = items.get(i).get_hora() + "hrs";
        viewHolder.txt_time.setText(time);

    }

    //Las veces que pintara el CardView
    @Override
    public int getItemCount() {
        return items.size();
    }
}
