package br.unaerp.compras.br.unaerp.compras.forms;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.atividades.MainActivity;
import br.unaerp.compras.br.unaerp.compras.dao.FornecedorDAO;
import br.unaerp.compras.br.unaerp.compras.dao.UtilsDAO;
import br.unaerp.compras.br.unaerp.compras.helper.FormularioFornecedorHelper;
import br.unaerp.compras.br.unaerp.compras.model.FornecedorModel;
import br.unaerp.compras.br.unaerp.compras.utils.Mask;

public class FormularioFornecedor extends AppCompatActivity {


    private FormularioFornecedorHelper helperFornecedor;
   // private EditText campoTelefone;
    //private EditText campoCnpj;
    private Spinner spinnerEstados;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_fornecedor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Formatar Campos*//*
        campoTelefone = (EditText) findViewById(R.id.form_fornecedor_telefone);
        campoTelefone.addTextChangedListener(Mask.insert(Mask.TELEFONE_MASK,campoTelefone));
        campoCnpj = (EditText) findViewById(R.id.form_fornecedor_cnpj);
        campoCnpj.addTextChangedListener(Mask.insert(Mask.CNPJ_MASK,campoCnpj));*/

        helperFornecedor = new FormularioFornecedorHelper(this);
        ArrayAdapter<String> adapter = carregarEstados();

        Intent editaFornecedor = getIntent();
        FornecedorModel fornecedor = (FornecedorModel) editaFornecedor.getSerializableExtra("fornecedor");
        if (fornecedor != null) {
            helperFornecedor.preencheFormulario(fornecedor,adapter);
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

    private ArrayAdapter<String> carregarEstados() {
        UtilsDAO dao = new UtilsDAO(this, MainActivity.versaoBD);
        List<String> estados = dao.selectEstado();
        spinnerEstados = (Spinner) findViewById(R.id.form_fornecedor_estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, estados);
        spinnerEstados.setAdapter(adapter);

        return adapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_salvar:

                String formValidado = helperFornecedor.validaFormulario();

                if(formValidado != null){
                    Toast.makeText(FormularioFornecedor.this, "Preencha o campo "+formValidado, Toast.LENGTH_SHORT).show();
                }else{
                    FornecedorModel fornecedor = helperFornecedor.getFornecedor();
                    FornecedorDAO dao = new FornecedorDAO(this, MainActivity.versaoBD);
                    if (fornecedor.getId() != null) {
                        dao.updateFornecedor(fornecedor);
                        Toast.makeText(this, String.valueOf("Fornecedor " + fornecedor.getNomeFantasia() + " alterado com Sucesso!"), Toast.LENGTH_SHORT).show();
                    } else {
                        dao.insertFornecedor(fornecedor);
                        Toast.makeText(this, String.valueOf("Fornecedor " + fornecedor.getNomeFantasia() + " salvo com Sucesso!"), Toast.LENGTH_SHORT).show();
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
}
