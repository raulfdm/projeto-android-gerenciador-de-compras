package br.unaerp.compras.br.unaerp.compras.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.fragment.ClienteFragment;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    private String actionBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        }else if(id == R.id.nav_clientes) {
            ClienteFragment cfrag = ClienteFragment.newInstance();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.linearlayout_for_fragment,
                    cfrag,
                    cfrag.getTag()
            ).commit();
        }else if(id == R.id.nav_produtos){

        }else if(id == R.id.nav_fornecedor){

        }else if(id == R.id.nav_vendas){

        }else if(id == R.id.nav_sinc){

        }else if(id == R.id.nav_sobre){
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setActionBarTitle(String actionBarTitle) {
        this.actionBarTitle = actionBarTitle;
    }

    @Override
    protected void onResume() {
        this.setTitle("");
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);

    }
}
