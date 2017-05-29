package vane.micasa.co.elementsaplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vane.micasa.co.elementsaplication.adapter.PerfumesAdapter;
import vane.micasa.co.elementsaplication.data.PerfumePojo;

/**
 * Created by Michael Garcia on 28/05/2017.
 */

public class ElementsList extends AppCompatActivity {
    private static View view;
    private static RecyclerView rv;
    private static PerfumesAdapter adapter;
    EditText nombre;
    EditText mililitros;
    EditText genero;
    FirebaseDatabase database;
    DatabaseReference perfumeRef;
    List<PerfumePojo> listPerfume;
    final FirebaseApp firebaseApp  = FirebaseApp.initializeApp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        nombre = (EditText) findViewById(R.id.nombrePerfume);
        mililitros = (EditText) findViewById(R.id.mililitrosPerfume);
        genero = (EditText) findViewById(R.id.generoPerfume);

        rv = (RecyclerView) findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        listPerfume = new ArrayList<>();


        adapter = new PerfumesAdapter(listPerfume);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        database = database.getInstance();
        perfumeRef = database.getReference(FirebaseReferences.PERFUME_REFERENCE);


        perfumeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LOG", dataSnapshot.toString());
                // String prueba = dataSnapshot.child("nombre").getValue(String.class);
                listPerfume.removeAll(listPerfume);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {
                    PerfumePojo perfume = snapshot.getValue(PerfumePojo.class);
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


    public void crearPerfume(View view) {
        PerfumePojo perfume = new PerfumePojo();
        perfume.setNombre(nombre.getText().toString());
        perfume.setGenero(genero.getText().toString());
        perfumeRef.push().setValue(perfume);
    }
}