package br.unaerp.compras.br.unaerp.compras.helper;

import android.widget.EditText;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.dao.ClienteDAO;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioClienteActivity;
import br.unaerp.compras.br.unaerp.compras.model.ClienteModel;

/**
 * Created by Raul on 10/04/2016.
 */
public class FormularioClienteHelper {

    private final EditText campoNome;
    private final EditText campoSobrenome;
    private final EditText campoTelefone;
    private final EditText campoApelido;
    private final EditText campoCelular;
    private final EditText campoEndereco;
    private final EditText campoEmail;

    private ClienteModel cliente;

    public FormularioClienteHelper(FormularioClienteActivity activity) {

        campoNome = (EditText) activity.findViewById(R.id.form_cliente_nome);
        campoSobrenome = (EditText) activity.findViewById(R.id.form_cliente_sobrenome);
        campoApelido = (EditText) activity.findViewById(R.id.form_cliente_apelido);
        campoTelefone = (EditText) activity.findViewById(R.id.form_cliente_telefone);
        campoCelular = (EditText) activity.findViewById(R.id.form_cliente_celular);
        campoEndereco = (EditText) activity.findViewById(R.id.form_cliente_endereco);
        campoEmail = (EditText) activity.findViewById(R.id.form_cliente_email);

        cliente = new ClienteModel();
    }

    public ClienteModel getCliente() {
        cliente.setNome(campoNome.getText().toString());
        cliente.setSobrenome(campoSobrenome.getText().toString());
        cliente.setApelido(campoApelido.getText().toString());
        cliente.setTelefone(campoTelefone.getText().toString());
        cliente.setCelular(campoCelular.getText().toString());
        cliente.setEndereco(campoEndereco.getText().toString());
        cliente.setEmail(campoEmail.getText().toString());

        return cliente;
    }

    public void preencheFormulario(ClienteModel cliente) {
        campoNome.setText(cliente.getNome());
        campoSobrenome.setText(cliente.getSobrenome());
        campoApelido.setText(cliente.getApelido());
        campoTelefone.setText(cliente.getTelefone());
        campoCelular.setText(cliente.getCelular());
        campoEndereco.setText(cliente.getEndereco());
        campoEmail.setText(cliente.getEmail());
        this.cliente = cliente;
    }


    public String validaFormulario() {
        if (campoNome.getText().toString() == null || campoNome.getText().toString().isEmpty()) {
            return "nome";
        } else if(campoSobrenome.getText().toString() == null || campoSobrenome.getText().toString().isEmpty()){
            return "sobrenome";
        }else if(campoTelefone.getText().toString() == null || campoTelefone.getText().toString().isEmpty() &&
                (campoCelular.getText().toString() == null || campoCelular.getText().toString().isEmpty())){
            return "telefone ou celular";
        }

        return null;
    }
}
