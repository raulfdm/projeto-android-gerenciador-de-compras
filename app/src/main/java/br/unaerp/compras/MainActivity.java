package br.unaerp.compras;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.unaerp.compras.br.unaerp.compras.atividades.ClientesActivity;

public class MainActivity extends AppCompatActivity {
    Button btnClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* com.github.clans.fab.FloatingActionButton menu1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_new_client);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormularioClienteActivity.class);
                startActivity(i);
            }
        });

        com.github.clans.fab.FloatingActionButton menu2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_new_sale);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormularioVendaActivity.class);
                startActivity(i);
            }
        });*/

        btnClientes = (Button) findViewById(R.id.main_btn_clientes);
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClientesActivity.class);
                startActivity(i);
            }
        });
    }

    boolean duploClick = false;

    @Override
    public void onBackPressed() {
        if (duploClick) {
            super.onBackPressed();
            return;
        }
        this.duploClick = true;
        Toast.makeText(this, "Clique novamente para sair..", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                duploClick = false;
            }
        }, 2000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_main:
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
