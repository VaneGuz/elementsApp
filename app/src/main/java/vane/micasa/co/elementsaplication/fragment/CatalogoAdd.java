package vane.micasa.co.elementsaplication.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
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
import vane.micasa.co.elementsaplication.data.PerfumePojo;
import vane.micasa.co.elementsaplication.dialog.DateDialog;
import vane.micasa.co.elementsaplication.dialog.DateFechaDisp;
import vane.micasa.co.elementsaplication.dialog.DateFechaPrepa;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogoAdd.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogoAdd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoAdd extends Fragment {
    EditText nombre;
    EditText genero;
    EditText casa;
    EditText fechaPreparacion;
    EditText fechaDisponible;
    EditText mililitros;
    DatabaseReference perfumeRef;
    FirebaseDatabase database;
    FloatingActionButton fab;
    private static View view;

    private OnFragmentInteractionListener mListener;

    public CatalogoAdd() {
        // Required empty public constructor
    }

    public static CatalogoAdd newInstance() {
        CatalogoAdd fragment = new CatalogoAdd();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Nuevo Perfume");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_catalogo_add, container, false);
        nombre = (EditText) view.findViewById(R.id.addNombre_catalogo);
        genero = (EditText) view.findViewById(R.id.addGenero_catalogo);
        casa = (EditText) view.findViewById(R.id.addCasa_catalogo);
        fechaDisponible = (EditText) view.findViewById(R.id.addFechaDisponible_catalogo);
        fechaPreparacion = (EditText) view.findViewById(R.id.addFechaPreparacion_catalogo);
        mililitros = (EditText) view.findViewById(R.id.addMililitros_catalogo);
        fab = ((MainActivity) getActivity()).getFloatingActionButton();
        if (fab != null) {
            fab.hide();
        }
        fechaDisponible.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DateFechaDisp();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        fechaPreparacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment newFragment = new DateFechaPrepa();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        database = database.getInstance();
        perfumeRef = database.getReference(FirebaseReferences.PERFUME_REFERENCE);
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
          void onFragmentInteraction(Uri uri);
    }

    public void crearPerfume() {
        PerfumePojo perfume = new PerfumePojo();
        perfume.setNombre(nombre.getText().toString());
        perfume.setGenero(genero.getText().toString());
        perfume.setCasa(casa.getText().toString());
        perfume.setMililitrosTotal(Long.parseLong(mililitros.getText().toString()));
        perfume.setFechaDisponible(fechaDisponible.getText().toString());
        perfume.setFechaPreparacion(fechaPreparacion.getText().toString());
        Log.i("TAG", "crear perfume fragment");
        //TODO Controlar cuando no se guarda con exito
        //TODO Controlar cuando la info es vacia
        perfumeRef.push().setValue(perfume);
        Snackbar.make(view, "Perfume creado con éxito", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new Catalogo()).commit();

    }
}
