package br.unaerp.compras.br.unaerp.compras.helper;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioFornecedor;
import br.unaerp.compras.br.unaerp.compras.model.FornecedorModel;

/**
 * Created by Raul on 10/04/2016.
 */
public class FormularioFornecedorHelper {

    private final EditText campoNomeFantasia;
    private final EditText campoCnpj;
    private final EditText campoEndereco;
    private final EditText campoNumero;
    private final EditText campoComplemento;
    private final EditText campoBairro;
    private final Spinner campoEstado;
    private final EditText campoCidade;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final EditText campoSite;


    private FornecedorModel fornecedor;

    public FormularioFornecedorHelper(FormularioFornecedor activity) {
        campoNomeFantasia = (EditText) activity.findViewById(R.id.form_fornecedor_nomefantasia);
        campoCnpj = (EditText) activity.findViewById(R.id.form_fornecedor_cnpj);
        campoEndereco = (EditText) activity.findViewById(R.id.form_fornecedor_endereco);
        campoNumero = (EditText) activity.findViewById(R.id.form_fornecedor_num);
        campoComplemento = (EditText) activity.findViewById(R.id.form_fornecedor_complemento);
        campoBairro = (EditText) activity.findViewById(R.id.form_fornecedor_bairro);
        campoEstado = (Spinner) activity.findViewById(R.id.form_fornecedor_estados);
        campoCidade = (EditText) activity.findViewById(R.id.form_fornecedor_cidade);
        campoTelefone = (EditText) activity.findViewById(R.id.form_fornecedor_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.form_fornecedor_email);
        campoSite = (EditText) activity.findViewById(R.id.form_fornecedor_site);

        fornecedor = new FornecedorModel();

    }

    public FornecedorModel getFornecedor() {
        fornecedor.setNomeFantasia(campoNomeFantasia.getText().toString());
        fornecedor.setCnpj(campoCnpj.getText().toString());
        fornecedor.setEndereco(campoEndereco.getText().toString());
        fornecedor.setNumero(campoNumero.getText().toString());
        fornecedor.setComplemento(campoComplemento.getText().toString());
        fornecedor.setBairro(campoBairro.getText().toString());
        fornecedor.setEstado(campoEstado.getSelectedItem().toString());
        fornecedor.setCidade(campoCidade.getText().toString());
        fornecedor.setTelefone(campoTelefone.getText().toString());
        fornecedor.setEmail(campoEmail.getText().toString());
        fornecedor.setSite(campoSite.getText().toString());

        return fornecedor;
    }

    public void preencheFormulario(FornecedorModel fornecedor,  ArrayAdapter<String> estados) {
        campoNomeFantasia.setText(fornecedor.getNomeFantasia());
        campoCnpj.setText(fornecedor.getCnpj());
        campoEndereco.setText(fornecedor.getEndereco());
        campoNumero.setText(fornecedor.getNumero());
        campoComplemento.setText(fornecedor.getComplemento());
        campoBairro.setText(fornecedor.getBairro());
        campoEstado.setSelection(estados.getPosition(fornecedor.getEstado()));
        campoCidade.setText(fornecedor.getCidade());
        campoTelefone.setText(fornecedor.getTelefone());
        campoEmail.setText(fornecedor.getEmail());
        campoSite.setText(fornecedor.getSite());
        this.fornecedor = fornecedor;
    }



    public String validaFormulario() {
        if (campoNomeFantasia.getText().toString() == null || campoNomeFantasia.getText().toString().isEmpty()) {
            return "Nome Fantasia";
        } else if (campoCnpj.getText().toString() == null || campoCnpj.getText().toString().isEmpty()) {
            return "CNPJ";
        } else if (campoEndereco.getText().toString() == null || campoEndereco.getText().toString().isEmpty()) {
            return "endereço";
        } else if (campoNumero.getText().toString() == null || campoNumero.getText().toString().isEmpty()) {
            return "número";
        } else if (campoCidade.getText().toString() == null || campoCidade.getText().toString().isEmpty()) {
            return "cidade";
        }

        return null;
    }
}
