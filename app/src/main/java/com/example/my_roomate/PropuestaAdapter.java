package com.example.my_roomate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropuestaAdapter extends RecyclerView.Adapter<PropuestaAdapter.ViewHolderPropuesta>  {

    private List<Propuesta> items;

    //Esta clase es una referencia al layout  recycler_template la plantilla
    public class ViewHolderPropuesta extends RecyclerView.ViewHolder {

        //campos respectivos al CardView
        public TextView txt_head, txt_body, ubicacion, precio;
        public ImageView cover;

        public ViewHolderPropuesta(View v){
            super(v);
            txt_head = v.findViewById(R.id.text_head_cv1);
            txt_body = v.findViewById(R.id.text_body_cv1);
            ubicacion = v.findViewById(R.id.text_lugar_cv1);
            precio = v.findViewById(R.id.text_precio_cv1);
            cover = v.findViewById(R.id.cover_card);
        }
    }

    //Funcion que me dara acceso al arreglo
    public PropuestaAdapter(List<Propuesta> items){
        this.items = items;
    }

    //ViewGroup es una colección de recursos, son las secciones de los espacios disponibles que estan en el recycler view
    //Aquí
    @NonNull
    @Override
    public ViewHolderPropuesta onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.propuesta_card_view, viewGroup, false);
        return new ViewHolderPropuesta(v);
    }


    // recibe dos parametros, nuestro item card o template y la posición donde quiero que se pinte
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPropuesta viewHolder, int i) {
        viewHolder.txt_head.setText(items.get(i).get_title());
        viewHolder.txt_body.setText(items.get(i).get_description());
        viewHolder.ubicacion.setText(items.get(i).get_localization());
        viewHolder.precio.setText(items.get(i).get_cost());
        viewHolder.cover.setImageResource(items.get(i).get_img());
    }

    //Las veces que pintara el CardView
    @Override
    public int getItemCount() {
        return items.size();
    }

}
