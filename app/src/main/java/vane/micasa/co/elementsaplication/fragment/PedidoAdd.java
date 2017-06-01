package vane.micasa.co.elementsaplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vane.micasa.co.elementsaplication.FirebaseReferences;
import vane.micasa.co.elementsaplication.MainActivity;
import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PedidoPojo;


public class PedidoAdd extends Fragment {
    private static View view;
    EditText persona;
    EditText perfume;
    EditText mililitros;
    EditText fechaEntrega;
    DatabaseReference pedidoRef;
    FirebaseDatabase database;
    private OnFragmentInteractionListener mListener;
    FloatingActionButton fab;

    public PedidoAdd() {
        // Required empty public constructor
    }


    public static PedidoAdd newInstance() {
        PedidoAdd fragment = new PedidoAdd();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Nuevo Pedido");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pedido_add, container, false);
        persona = (EditText) view.findViewById(R.id.addPersona_pedido);
        perfume = (EditText) view.findViewById(R.id.addPerfume_pedido);
        fechaEntrega = (EditText) view.findViewById(R.id.addFechaEntrega_pedido);
        mililitros = (EditText) view.findViewById(R.id.addMililitros_pedido);
        fab = ((MainActivity) getActivity()).getFloatingActionButton();
        if (fab != null) {
           fab.hide();
        }
        database = database.getInstance();
        pedidoRef = database.getReference(FirebaseReferences.PEDIDO_REFERENCE);
        if (fab != null) {
            fab.hide();
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void crearPedido() {
        PedidoPojo pedido = new PedidoPojo();
        pedido.setNombre(persona.getText().toString());
        pedido.setPerfume(perfume.getText().toString());
        pedido.setFechaEntrega(fechaEntrega.getText().toString());
        pedido.setMililitros(Long.parseLong(mililitros.getText().toString()));
        //TODO Controlar cuando no se guarda con exito
        pedidoRef.push().setValue(pedido);
        Snackbar.make(view, "Pedido creado con éxito", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new Pedido()).commit();

    }

}
