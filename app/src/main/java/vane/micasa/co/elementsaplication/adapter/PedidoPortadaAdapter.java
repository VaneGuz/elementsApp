package vane.micasa.co.elementsaplication.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PedidoPojo;

/**
 * Created by Michael Garcia on 29/05/2017.
 */

public class PedidoPortadaAdapter extends RecyclerView.Adapter<PedidoPortadaAdapter.PedidoViewHolder> {

    private static ClickListener clickListener;
    private List<PedidoPojo> listPedidoPortada;
    private Date fecha;

    public PedidoPortadaAdapter(List<PedidoPojo> listPedidoPortada) {

        this.listPedidoPortada = listPedidoPortada;
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView nombre;
        TextView perfume;
        TextView mililitros;
        TextView fechaEntrega;
        LinearLayout layout;
        //   SwitchCompat aSwitch;


        public PedidoViewHolder(View itemView) {
            super(itemView);
            this.layout = (LinearLayout) itemView.findViewById(R.id.layout_row_portada);
            this.nombre = (TextView) itemView.findViewById(R.id.nombre_portada);
            this.perfume = (TextView) itemView.findViewById(R.id.perfume_portada);
            this.mililitros = (TextView) itemView.findViewById(R.id.ml_portada);
            this.fechaEntrega = (TextView) itemView.findViewById(R.id.fecha_portada);
//            aSwitch = (SwitchCompat) itemView.findViewById(R.id.switcher);
//            aSwitch.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

        @Override    //paso2
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
            //mostrarDetalle(getAdapterPosition(), v);
            Log.i("ENTROO ", "onclick");
        }
//
//        private void mostrarDetalle(int adapterPosition, View v) {
//            if (aSwitch.isChecked()) {
//                Snackbar.make(v, "PEDIDO pagado", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            } else {
//                Snackbar.make(v, "PEDIDO no pago", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            }
//        }

        @Override
        public boolean onLongClick(View v) {
            Log.i("ENTROO ", "ONLONGCLICK  ENTROOOO");
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        Log.i("ENTROO ", "entro a setitemclick en EventsAdapter");
        PedidoPortadaAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }


    @Override
    public PedidoPortadaAdapter.PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pedido_portada, parent, false);
        PedidoPortadaAdapter.PedidoViewHolder holder = new PedidoPortadaAdapter.PedidoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PedidoPortadaAdapter.PedidoViewHolder holder, int position) {
        PedidoPojo pedido = listPedidoPortada.get(position);
        if (pedido.getEstado() != 1) {
            String str = Long.toString(pedido.getMililitros());
            holder.nombre.setText(pedido.getNombre());
            holder.perfume.setText(pedido.getPerfume());
            holder.mililitros.setText(str);
            holder.fechaEntrega.setText(pedido.getFechaEntrega());
            try {
                fecha = (new SimpleDateFormat("dd/MM/yyyy")).parse(pedido.getFechaEntrega());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (compararFecha(fecha)) {
                holder.layout.setBackgroundColor(0xFFFEE2E1);
            } else {
                holder.layout.setBackgroundColor(0xFFFEFCE1);
            }

        }
        //   holder.genero.setText(pedido.getGenero());
    }

    public boolean compararFecha(Date fecha) {

        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.DAY_OF_YEAR, -1);
        hoy.set(Calendar.HOUR, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        Calendar manana = Calendar.getInstance();
        manana.add(Calendar.DAY_OF_YEAR, 1);
        manana.set(Calendar.HOUR, 0);
        manana.set(Calendar.MINUTE, 0);
        manana.set(Calendar.SECOND, 0);
        if (fecha.after(hoy.getTime()) && fecha.before(manana.getTime())) {
            return true;
        }
        return false;
    }


    @Override
    public int getItemCount() {
        return listPedidoPortada.size();
    }


}
