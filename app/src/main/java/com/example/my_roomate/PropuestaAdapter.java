package com.example.my_roomate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.my_roomate.Model.Proposel;

import java.util.ArrayList;
import java.util.List;

public class PropuestaAdapter extends RecyclerView.Adapter<PropuestaAdapter.ViewHolderPropuesta>  {

    private List<Proposel> items;
    private Context context;

    //Esta clase es una referencia al layout  recycler_template la plantilla
    public class ViewHolderPropuesta extends RecyclerView.ViewHolder {

        //campos respectivos al CardView
        public TextView txt_head, txt_body, ubicacion, precio;
        public ImageView cover;
        public RatingBar favBar;

        public ViewHolderPropuesta(View v){
            super(v);
            txt_head = v.findViewById(R.id.text_head_cv1);
            txt_body = v.findViewById(R.id.text_body_cv1);
            ubicacion = v.findViewById(R.id.text_lugar_cv1);
            precio = v.findViewById(R.id.text_precio_cv1);
            favBar = v.findViewById(R.id.fav_bar);
            cover = v.findViewById(R.id.cover_card);
        }
    }

    //Funcion que me dara acceso al arreglo
    public PropuestaAdapter(Context context){
        this.context = context;
        items = new ArrayList<>();
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
        viewHolder.txt_head.setText(items.get(i).getTitle());
        viewHolder.txt_body.setText(items.get(i).getBrief());
        viewHolder.ubicacion.setText(items.get(i).getLocalization());
        viewHolder.precio.setText(items.get(i).getCost());
        viewHolder.favBar.setRating(Float.parseFloat(items.get(i).getRanking()));
        //Descargar la imagen cover que llega del servidor
        Glide.with(context)
                .load(items.get(i).getImg_cover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholderimg)
                .into(viewHolder.cover);
    }

    //Las veces que pintara el CardView
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Aquí se agregaran las prupuestas cuando la petición del webservice llegue
    public void adicionarListaPropuesta(List<Proposel> listaPropuestas){
        items.addAll(listaPropuestas); //me agrega todos los datos que llegaron de la petición
        notifyDataSetChanged(); // esta función permite actulizar los datos del recicler view
    }

}
