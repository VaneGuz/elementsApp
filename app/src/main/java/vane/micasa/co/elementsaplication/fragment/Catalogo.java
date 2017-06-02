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
import vane.micasa.co.elementsaplication.adapter.PerfumesAdapter;
import vane.micasa.co.elementsaplication.R;
import vane.micasa.co.elementsaplication.data.PerfumePojo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Catalogo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Catalogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Catalogo extends Fragment {
    private static View view;
    private static RecyclerView rv;
    private static PerfumesAdapter adapter;
    EditText nombre;
    EditText mililitros;
    EditText genero;
    FirebaseDatabase database;
    DatabaseReference perfumeRef;
    List<PerfumePojo> listPerfume;
    FloatingActionButton fab;
//    final FirebaseApp firebaseApp  = FirebaseApp.initializeApp(this);

    private OnFragmentInteractionListener mListener;

    public Catalogo() {
    }


    public static Catalogo newInstance() {
        Catalogo fragment = new Catalogo();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Catalogo");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        populateRecyclerView();
        fab = ((MainActivity) getActivity()).getFloatingActionButton();
        if (fab != null) {
            fab.setImageResource(R.drawable.ic_mas);
            fab.show();
        }
        return view;
    }

    private void populateRecyclerView() {
        rv = (RecyclerView) view.findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        listPerfume = new ArrayList<>();

        adapter = new PerfumesAdapter(listPerfume);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        database = database.getInstance();
        perfumeRef = database.getReference(FirebaseReferences.PERFUME_REFERENCE);

        updateList();
    }

    public List<PerfumePojo> getListPerfume() {
        return listPerfume;
    }

    public void updateList() {
        perfumeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LOG", dataSnapshot.toString());
                // String prueba = dataSnapshot.child("nombre").getValue(String.class);
                listPerfume.removeAll(listPerfume);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {
                    PerfumePojo perfume = snapshot.getValue(PerfumePojo.class);
                    perfume.setKey(snapshot.getKey());
                    listPerfume.add(perfume);
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
