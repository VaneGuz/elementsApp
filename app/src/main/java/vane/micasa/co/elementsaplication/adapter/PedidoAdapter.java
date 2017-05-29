package vane.micasa.co.elementsaplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PedidoPojo;

/**
 * Created by Michael Garcia on 29/05/2017.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    public PedidoAdapter(List<PedidoPojo> listPedido) {
        this.listPedido = listPedido;
    }
    public static class PedidoViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView perfume;
        TextView mililitros;

        public PedidoViewHolder(View itemView){
            super(itemView);

            this.nombre= (TextView) itemView.findViewById(R.id.nombre_pedido);
            this.perfume= (TextView) itemView.findViewById(R.id.perfume_pedido);
            this.mililitros= (TextView) itemView.findViewById(R.id.mililitros_pedido);

        }
    }

    List<PedidoPojo> listPedido;

    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pedido,parent,false);
        PedidoAdapter.PedidoViewHolder holder = new PedidoAdapter.PedidoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PedidoAdapter.PedidoViewHolder holder, int position) {
        PedidoPojo pedido = listPedido.get(position);

        holder.nombre.setText(pedido.getNombre());
        holder.perfume.setText(pedido.getPerfume());
        holder.mililitros.setText(pedido.getMililitros().toString());
     //   holder.genero.setText(pedido.getGenero());
    }

    @Override
    public int getItemCount() {
        return listPedido.size();
    }


}
