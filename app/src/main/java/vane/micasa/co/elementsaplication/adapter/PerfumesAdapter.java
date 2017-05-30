package vane.micasa.co.elementsaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PerfumePojo;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PerfumesAdapter extends RecyclerView.Adapter<PerfumesAdapter.PerfumesViewHolder> {
    public PerfumesAdapter(List<PerfumePojo> listPerfume) {
        this.listPerfume = listPerfume;
    }
    public static class PerfumesViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView genero;
        TextView casa;
        TextView mililitros;
        TextView fechaPreparacion;
        TextView fechaDisponible;

        public PerfumesViewHolder(View itemView){
            super(itemView);
            this.nombre = (TextView) itemView.findViewById(R.id.nombre_catalogo);
            this.genero= (TextView) itemView.findViewById(R.id.genero_catalogo);
            this.casa= (TextView) itemView.findViewById(R.id.casa_catalogo);
            this.mililitros= (TextView) itemView.findViewById(R.id.mililitros_catalogo);
            this.fechaPreparacion= (TextView) itemView.findViewById(R.id.fechaPrepa_catalogo);
            this.fechaDisponible= (TextView) itemView.findViewById(R.id.fechaDisp_catalogo);

        }
    }

    List<PerfumePojo> listPerfume;

    @Override
    public PerfumesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perfume,parent,false);
        PerfumesViewHolder holder = new PerfumesViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PerfumesViewHolder holder, int position) {
    PerfumePojo perfume = listPerfume.get(position);
        String str = Long.toString(perfume.getMililitrosTotal());
        holder.nombre.setText(perfume.getNombre());
        holder.genero.setText(perfume.getGenero());
        holder.casa.setText(perfume.getCasa());
        holder.mililitros.setText(str);
        holder.fechaDisponible.setText(perfume.getFechaDisponible());
        holder.fechaPreparacion.setText(perfume.getFechaPreparacion());
    }

    @Override
    public int getItemCount() {
        return listPerfume.size();
    }


}
