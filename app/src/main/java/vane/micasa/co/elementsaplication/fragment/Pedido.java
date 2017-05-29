package vane.micasa.co.elementsaplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

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
import vane.micasa.co.elementsaplication.adapter.PedidoAdapter;
import vane.micasa.co.elementsaplication.adapter.PerfumesAdapter;
import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PedidoPojo;
import vane.micasa.co.elementsaplication.data.PerfumePojo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pedido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pedido extends Fragment {

    private static View view;
    private static RecyclerView rv;
    private static PedidoAdapter adapter;
    EditText nombre;
    EditText mililitros;
    EditText perfume;
    FirebaseDatabase database;
    DatabaseReference pedidoRef;
    List<PedidoPojo> listPedido;
    private OnFragmentInteractionListener mListener;

    public Pedido() {
        // Required empty public constructor
    }

    public static Pedido newInstance() {
        Pedido fragment = new Pedido();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pedido, container, false);
        populateRecyclerView();
        return view;
    }

    private void populateRecyclerView() {
        rv = (RecyclerView) view.findViewById(R.id.recycler_pedido);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        listPedido = new ArrayList<>();

        adapter = new PedidoAdapter(listPedido);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        database = database.getInstance();
        pedidoRef = database.getReference(FirebaseReferences.PEDIDO_REFERENCE);

        pedidoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LOG", dataSnapshot.toString());
                // String prueba = dataSnapshot.child("nombre").getValue(String.class);
                listPedido.removeAll(listPedido);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {
                    Log.i("TAG", snapshot.toString());
                    PedidoPojo pedido = snapshot.getValue(PedidoPojo.class);
                    listPedido.add(pedido);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LOG", "ERRRROOOOR");
            }
        });
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
}