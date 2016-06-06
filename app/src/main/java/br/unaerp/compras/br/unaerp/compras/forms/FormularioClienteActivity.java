package br.unaerp.compras.br.unaerp.compras.forms;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.helper.FormularioClienteHelper;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

public class FormularioClienteActivity extends AppCompatActivity {

    private FormularioClienteHelper helperCliente;
    private EditText campoTelefone;
    private EditText campoCelular;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helperCliente = new FormularioClienteHelper(this);
        campoTelefone = (EditText) findViewById(R.id.form_cliente_telefone);
        campoTelefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
        campoCelular = (EditText) findViewById(R.id.form_cliente_celular);
        campoCelular.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
        Intent editaCliente = getIntent();
        ClienteModel cliente = (ClienteModel) editaCliente.getSerializableExtra("cliente");
        if (cliente != null) {
            helperCliente.preencheFormulario(cliente);
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
            case R.id.ok_form_cliente:

                String formValidado = helperCliente.validaFormulario();

                if(formValidado != null){
                    Toast.makeText(FormularioClienteActivity.this, "Preencha o campo "+formValidado, Toast.LENGTH_SHORT).show();
                }else{
                    ClienteModel cliente = helperCliente.getCliente();
                    ClienteDAO dao = new ClienteDAO(this);
                    if (cliente.getId() != null) {
                        dao.updateCliente(cliente);
                        Toast.makeText(this, String.valueOf("Cliente " + cliente.getNome() + " Alterado com Sucesso!"), Toast.LENGTH_SHORT).show();
                    } else {
                        dao.insertCliente(cliente);
                        Toast.makeText(this, String.valueOf("Cliente " + cliente.getNome() + " Salvo com Sucesso!"), Toast.LENGTH_SHORT).show();
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
