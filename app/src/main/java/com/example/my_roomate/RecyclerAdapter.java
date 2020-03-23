package com.example.my_roomate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//ViewHolder datos es una sub clase de RecyclerData que hereda de ViewHolder
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderDatos> {


    ArrayList<String> datos; //Arreglo que manejara los datos internos para hacerlos externos
    onItemClickListenner event;

    //constructor
    public RecyclerAdapter(ArrayList<String> _datos, onItemClickListenner _event){
        datos = _datos;
        event = _event;
    }


    public interface onItemClickListenner{
        void onItemClick(int index);
    }

    //Esta clase es una referencia al layout  recycler_template la plantilla
    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {
        //vinculo los recursos del layout de nuestro recycler_template
        TextView txtDato;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtDato = itemView.findViewById(R.id.textCardview);
        }
        //bindeo los datos
        public void bind(String s){
            txtDato.setText(s);
        }

        @Override
        public void onClick(View v) {
            int itemIndex = getAdapterPosition();
            event.onItemClick(itemIndex);
        }
    }

    //ViewGroup es una colección de recursos, son las secciones de los espacios disponibles que estan en el recycler view

    //Aquí
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_template, null, false);
        return new ViewHolderDatos(v);
    }


    // recibe dos parametros, nuestro item card o template y la posición donde quiero que se pinte
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.bind(datos.get(position));
    }

    @Override
    //Cuantas iteraciones va a dar
    public int getItemCount() {
        return datos.size();
    }


}
