package vane.micasa.co.elementsaplication;


import android.app.FragmentManager;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;

import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import vane.micasa.co.elementsaplication.data.PerfumePojo;
import vane.micasa.co.elementsaplication.fragment.CatalogoAdd;
import vane.micasa.co.elementsaplication.fragment.ContableAdd;
import vane.micasa.co.elementsaplication.fragment.Catalogo;
import vane.micasa.co.elementsaplication.fragment.Contable;
import vane.micasa.co.elementsaplication.fragment.Pedido;
import vane.micasa.co.elementsaplication.fragment.PedidoAdd;
import vane.micasa.co.elementsaplication.fragment.Portada;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    FloatingActionButton fab;
    Fragment pedidoFrag;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_portada);

    }
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_portada:
                fragment = new Portada();
                break;
            case R.id.nav_pedido:
                fragment = new Pedido();
                break;
            case R.id.nav_contable:
                fragment = new Contable();
                break;
            case R.id.nav_catalogo:
                fragment = new Catalogo();
                break;
        }

        if (fragment != null) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void onFABClick(View view) {
        Fragment frag = getFragmentManager().findFragmentById(R.id.content_frame);

        if (frag instanceof Portada) {
            Log.i("TAG", "encontrado el actual fragment portada");
        } else if (frag instanceof Pedido) {
            fragment = new PedidoAdd();
            Log.i("TAG", "encontrado el actual fragment pedido");
        } else if (frag instanceof Contable) {
            fragment = new ContableAdd();
            Log.i("TAG", "encontrado el actual fragment contable");
        } else if (frag instanceof Catalogo) {
            fragment = new CatalogoAdd();
            Log.i("TAG", "encontrado el actual fragment catalogo");
        }
        if (fragment != null) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//            getSupportActionBar().setTitle(title);
        }
    }

    public void crearPerfume(View view) {
        CatalogoAdd cat = (CatalogoAdd) getFragmentManager().findFragmentById(R.id.content_frame);
        Log.i("TAG", "crear perfume");
        cat.crearPerfume();
    }

    public void crearPedido(View view) {
        PedidoAdd cat = (PedidoAdd) getFragmentManager().findFragmentById(R.id.content_frame);
        Log.i("TAG", "crear perfume");
        cat.crearPedido();
    }
    public void mostrarCatalogo(View view) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Catalogo()).commit();
    }
    public void mostrarPedido(View view) {
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Pedido()).commit();
    }

    public void showFloatingActionButton() {
        fab.show();
    }

    ;

    public void hideFloatingActionButton() {
        fab.hide();
    }

    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }
}



