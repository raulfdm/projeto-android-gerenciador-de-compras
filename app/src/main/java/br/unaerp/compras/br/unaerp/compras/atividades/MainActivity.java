package br.unaerp.compras.br.unaerp.compras.atividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.dao.FornecedorDAO;
import br.unaerp.compras.br.unaerp.compras.dao.LoginDAO;
import br.unaerp.compras.br.unaerp.compras.dao.ProdutoDAO;
import br.unaerp.compras.br.unaerp.compras.dao.UtilsDAO;
import br.unaerp.compras.br.unaerp.compras.fragment.ClienteFragment;
import br.unaerp.compras.br.unaerp.compras.fragment.DashboardFragment;
import br.unaerp.compras.br.unaerp.compras.fragment.FornecedorFragment;
import br.unaerp.compras.br.unaerp.compras.fragment.ProdutoFragment;
import br.unaerp.compras.br.unaerp.compras.fragment.VendaFragment;
import br.unaerp.compras.br.unaerp.compras.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    public static int versaoBD = 12;
    boolean duploClick = false;
    FragmentManager manager = getSupportFragmentManager();

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


        carregaBancoDeDados();
        abrirDashboard();

    }

    private void carregaBancoDeDados() {
        FornecedorDAO fdao = new FornecedorDAO(this, versaoBD);
        ClienteDAO cdao = new ClienteDAO(this, versaoBD);
        LoginDAO ldao = new LoginDAO(this, versaoBD);
        UtilsDAO udao = new UtilsDAO(this, versaoBD);
        ProdutoDAO pdao = new ProdutoDAO(this, versaoBD);


        fdao.startaBD(this.versaoBD);
        cdao.startaBD(this.versaoBD);
        ldao.startaBD(this.versaoBD);
        udao.startaBD(this.versaoBD);
        pdao.startaBD(this.versaoBD);
    }

   @Override
    public void onBackPressed() {

        if (duploClick) {
            super.onBackPressed();
            return;
        }
        duploClick = true;
        Toast.makeText(this, "Clique novamente para sair..", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                duploClick = false;
            }
        }, 2000);

    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }*/


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_dashboard) {
            abrirDashboard();
        } else if (id == R.id.nav_clientes) {
            abrirCliente();
        } else if (id == R.id.nav_produtos) {
            abrirProduto();
        } else if (id == R.id.nav_fornecedor) {
            abrirFornecedor();
        } else if (id == R.id.nav_vendas) {
            abrirVendas();
        } else if (id == R.id.nav_sinc) {
            metodoParaEnviarParaServidor();
        } else if (id == R.id.nav_sobre) {
            abrirSobre();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*ABETURA DE TELAS*/
    private void abrirSobre() {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    private void abrirVendas() {
        VendaFragment vendaFrag = VendaFragment.newInstance();
        this.manager.beginTransaction().replace(
                R.id.linearlayout_for_fragment,
                vendaFrag,
                vendaFrag.getTag()
        ).commit();
    }

    private void abrirFornecedor() {
        FornecedorFragment fornecedorFrag = FornecedorFragment.newInstance();
        this.manager.beginTransaction().replace(
                R.id.linearlayout_for_fragment,
                fornecedorFrag,
                fornecedorFrag.getTag()
        ).commit();
    }

    private void abrirProduto() {
        ProdutoFragment produtoFrag = ProdutoFragment.newInstance();
        this.manager.beginTransaction().replace(
                R.id.linearlayout_for_fragment,
                produtoFrag,
                produtoFrag.getTag()
        ).commit();
    }

    private void abrirCliente() {
        ClienteFragment clienteFrag = ClienteFragment.newInstance();
        manager.beginTransaction().replace(
                R.id.linearlayout_for_fragment,
                clienteFrag,
                clienteFrag.getTag()
        ).commit();
    }

    private void abrirDashboard() {
        DashboardFragment dashFrag = DashboardFragment.newInstance();
        this.manager.beginTransaction().replace(
                R.id.linearlayout_for_fragment,
                dashFrag,
                dashFrag.getTag()
        ).commit();
    }

    private void metodoParaEnviarParaServidor() {
/*        MenuItem enviarSv = menu.add("Enviar para o Servidor");
        enviarSv.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                EnviaClienteTask enviaClienteTask = new EnviaClienteTask(ClienteFragment.this.getActivity(), cliente);
                enviaClienteTask.execute();
                return Boolean.parseBoolean(null);
            }
        });*/
        Toast.makeText(MainActivity.this, "Enviar para o Servidor! Pendente...", Toast.LENGTH_SHORT).show();
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
