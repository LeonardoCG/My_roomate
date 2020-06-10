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

public class MyroomAdapter extends RecyclerView.Adapter<MyroomAdapter.ViewHolderMyroom> implements View.OnClickListener {

    private List<Proposel> items;
    private Context context;
    private View.OnClickListener listener; //variable que me servira para generar de forma manual el onclick listener


    public class ViewHolderMyroom extends RecyclerView.ViewHolder {

        public TextView txt_head, txt_body, ubicacion, precio;
        public ImageView cover;
        public RatingBar favBar;

        public ViewHolderMyroom(@NonNull View v) {
            super(v);
            txt_head = v.findViewById(R.id.text_head_cv3);
            txt_body = v.findViewById(R.id.text_body_cv3);
            ubicacion = v.findViewById(R.id.text_lugar_cv3);
            precio = v.findViewById(R.id.text_precio_cv3);
            favBar = v.findViewById(R.id.fav_bar2);
            cover = v.findViewById(R.id.cover_card);
        }
    }

    //Funcion que me dara acceso al arreglo
    public MyroomAdapter(Context context){
        this.context = context;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderMyroom onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_rooms_card_view, viewGroup, false);
        v.setOnClickListener(this);
        return new MyroomAdapter.ViewHolderMyroom(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMyroom viewHolder, int i) {
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

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Aquí se agregaran las prupuestas cuando la petición del webservice llegue
    public void adicionarListaPropuesta(List<Proposel> listaPropuestas){
        items.addAll(listaPropuestas); //me agrega todos los datos que llegaron de la petición
        notifyDataSetChanged(); // esta función permite actulizar los datos del recicler view
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

}
