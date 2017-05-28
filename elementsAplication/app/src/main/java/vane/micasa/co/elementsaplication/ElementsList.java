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

/**
 * Created by Michael Garcia on 28/05/2017.
 */

public class ElementsList extends AppCompatActivity {
    RecyclerView rv;
    EditText nombre;
    EditText mililitros;
    EditText genero;
    FirebaseDatabase database;
    DatabaseReference perfumeRef;
    List<Perfume> listPerfume;
    final FirebaseApp firebaseApp  = FirebaseApp.initializeApp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        nombre = (EditText) findViewById(R.id.nombrePerfume);
        mililitros = (EditText) findViewById(R.id.mililitrosPerfume);
        genero = (EditText) findViewById(R.id.generoPerfume);
        rv = (RecyclerView) findViewById(R.id.recycler);
        final PerfumesAdapter adapter;


        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        listPerfume = new ArrayList<>();


        adapter = new PerfumesAdapter(listPerfume);
        rv.setAdapter(adapter);

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
                    Perfume perfume = snapshot.getValue(Perfume.class);
                    listPerfume.add(perfume);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LOG", "ERRRROOOOR");
            }
        });

        Log.i("LOG", "entroooooo2");

    }


    public void crearPerfume(View view) {
        Perfume perfume = new Perfume();
        perfume.setNombre(nombre.getText().toString());
        perfume.setGenero(genero.getText().toString());


        perfumeRef.push().setValue(perfume);


    }
}