package vane.micasa.co.elementsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PerfumesAdapter extends RecyclerView.Adapter<PerfumesAdapter.PerfumesViewHolder> {
    public PerfumesAdapter(List<Perfume> listPerfume) {
        this.listPerfume = listPerfume;
    }
    public static class PerfumesViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView genero;

        public PerfumesViewHolder(View itemView){
            super(itemView);
            this.nombre = (TextView) itemView.findViewById(R.id.textViewPerfume);
            this.genero= (TextView) itemView.findViewById(R.id.textViewgenero);

        }
    }

    List<Perfume> listPerfume;

    @Override
    public PerfumesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events,parent,false);
        PerfumesViewHolder holder = new PerfumesViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PerfumesViewHolder holder, int position) {
    Perfume perfume = listPerfume.get(position);
        holder.nombre.setText(perfume.getNombre());
        holder.genero.setText(perfume.getGenero());
    }

    @Override
    public int getItemCount() {
        return listPerfume.size();
    }


}
