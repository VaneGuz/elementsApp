package vane.micasa.co.elementsaplication.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PedidoPojo;

/**
 * Created by Michael Garcia on 29/05/2017.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private static ClickListener clickListener;
    private List<PedidoPojo> listPedido;
    static Context context;

    public PedidoAdapter(List<PedidoPojo> listPedido) {

        this.listPedido = listPedido;
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView nombre;
        TextView perfume;
        TextView mililitros;
        TextView fechaEntrega;
        SwitchCompat aSwitch;


        public PedidoViewHolder(View itemView) {
            super(itemView);

            this.nombre = (TextView) itemView.findViewById(R.id.nombre_pedido);
            this.perfume = (TextView) itemView.findViewById(R.id.perfume_pedido);
            this.mililitros = (TextView) itemView.findViewById(R.id.mililitros_pedido);
            this.fechaEntrega = (TextView) itemView.findViewById(R.id.fechaEntrega_pedido);
            aSwitch = (SwitchCompat) itemView.findViewById(R.id.switcher);
            aSwitch.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

        @Override    //paso2
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);

            mostrarDetalle(getAdapterPosition(), v);
            Log.i("ENTROO ", "onclick");
        }

        private void mostrarDetalle(int adapterPosition, View v) {
            if (aSwitch.isChecked()) {
                Snackbar.make(v, "PEDIDO pagado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            } else {
                Snackbar.make(v, "PEDIDO no pago", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i("ENTROO ", "ONLONGCLICK  ENTROOOO");
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        Log.i("ENTROO ", "entro a setitemclick en EventsAdapter");
        PedidoAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }


    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pedido, parent, false);
        PedidoAdapter.PedidoViewHolder holder = new PedidoAdapter.PedidoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PedidoAdapter.PedidoViewHolder holder, int position) {
        PedidoPojo pedido = listPedido.get(position);
        String str = Long.toString(pedido.getMililitros());
        holder.nombre.setText(pedido.getNombre());
        holder.perfume.setText(pedido.getPerfume());
        holder.mililitros.setText(str);
        holder.fechaEntrega.setText(pedido.getFechaEntrega());
        if (pedido.getEstado() == 1) {
            holder.aSwitch.setChecked(true);
        } else {
            holder.aSwitch.setChecked(false);
        }
        //   holder.genero.setText(pedido.getGenero());
    }

    @Override
    public int getItemCount() {
        return listPedido.size();
    }


}
