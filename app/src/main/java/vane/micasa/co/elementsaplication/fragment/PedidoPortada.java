package vane.micasa.co.elementsaplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vane.micasa.co.elementsaplication.FirebaseReferences;
import vane.micasa.co.elementsaplication.MainActivity;
import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.adapter.PedidoPortadaAdapter;
import vane.micasa.co.elementsaplication.data.PedidoPojo;


public class PedidoPortada extends Fragment {

    FloatingActionButton fab;


    private static RecyclerView rv;
    private static PedidoPortadaAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference pedidoRef;
    List<PedidoPojo> listPedidoPortada;

    private static View view;
    private OnFragmentInteractionListener mListener;

    public PedidoPortada() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PedidoPortada newInstance() {
        PedidoPortada fragment = new PedidoPortada();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Elements App");
        listPedidoPortada= new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_portada, container, false);
        populateRecyclerView();
        fab = ((MainActivity) getActivity()).getFloatingActionButton();
        if (fab != null) {
            fab.setImageResource(R.drawable.ic_mas);
            fab.hide();
        }
        return view;
    }

    private void populateRecyclerView() {
        rv = (RecyclerView) view.findViewById(R.id.recycler_portada);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PedidoPortadaAdapter(listPedidoPortada);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        database = database.getInstance();
        pedidoRef = database.getReference(FirebaseReferences.PEDIDO_REFERENCE);

        updateList();

        adapter.setOnItemClickListener(new PedidoPortadaAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("TAG", "onItemClick position: " + position);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.i("TAG", "onItemLongClick pos = " + position);
            }
        });
    }

    public void updateList() {
        //TODO Este metodo debe de llamar a la lista de pedidos local, no hacer otra peticion
        pedidoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LOG", dataSnapshot.toString());
                // String prueba = dataSnapshot.child("nombre").getValue(String.class);
                listPedidoPortada.removeAll(listPedidoPortada);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {

                    int estado= snapshot.getValue(PedidoPojo.class).getEstado();
                    if(estado!=1){
                        PedidoPojo pedido = snapshot.getValue(PedidoPojo.class);
                        pedido.setKey(snapshot.getKey());
                        listPedidoPortada.add(pedido);

                    }


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LOG", "ERRRROOOOR");
            }
        });
    }
    public List<PedidoPojo> getListPedido() {
        return listPedidoPortada;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
