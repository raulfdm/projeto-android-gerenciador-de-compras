package br.unaerp.compras.br.unaerp.compras.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.dao.ProdutoDAO;
import br.unaerp.compras.br.unaerp.compras.helper.ProdutoHelper;
import br.unaerp.compras.br.unaerp.compras.model.ProdutoModel;

public class FormularioProduto extends AppCompatActivity {

    private ProdutoHelper helperProduto;
    private Spinner spinnerFornecedor;
    private Spinner spinnerTamanho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helperProduto = new ProdutoHelper(this);


        ArrayAdapter<CharSequence> adaptarTamanho = carregaTamanhos();
        ArrayAdapter<String> adapterFornecedor = carregarFornecedor();

        Intent editaProduto = getIntent();
        ProdutoModel produto = (ProdutoModel) editaProduto.getSerializableExtra("produto");
        if (produto != null) {
            this.helperProduto.preencheFormulario(produto, adaptarTamanho, adapterFornecedor);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.form_cliente_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_salvar:
                String formValidado = helperProduto.validaFormulario();
                if (formValidado != "sucesso") {
                    Toast.makeText(FormularioProduto.this, "Preencha o campo " + formValidado, Toast.LENGTH_SHORT).show();
                } else {
                    ProdutoModel produto = helperProduto.getProduto();
                    ProdutoDAO dao = new ProdutoDAO(this, MainActivity.versaoBD);
                    if (produto.getId() != null) {
                        dao.updateProduto(produto);
                        Toast.makeText(this, String.valueOf("Produto " + produto.getDescricao() + " Alterado com Sucesso!"), Toast.LENGTH_SHORT).show();
                    } else {
                        dao.insertProduto(produto);
                        Toast.makeText(this, String.valueOf("Produto " + produto.getDescricao() + " Salvo com Sucesso!"), Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                    finish();
                }

                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayAdapter<String> carregarFornecedor() {
        ProdutoDAO dao = new ProdutoDAO(this, MainActivity.versaoBD);
        List<String> fornecedores = dao.selectFornecedores();
        spinnerFornecedor = (Spinner) findViewById(R.id.form_produto_fornecedor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fornecedores);
        spinnerFornecedor.setAdapter(adapter);
        return adapter;
    }

    private ArrayAdapter<CharSequence> carregaTamanhos() {
        spinnerTamanho = (Spinner) findViewById(R.id.form_produto_tamanho);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.produto_tamanho, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTamanho.setAdapter(adapter);
        return adapter;
    }
}
