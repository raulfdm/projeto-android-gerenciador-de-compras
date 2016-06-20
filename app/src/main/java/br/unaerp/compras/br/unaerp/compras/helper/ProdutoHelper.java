package br.unaerp.compras.br.unaerp.compras.helper;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import br.unaerp.compras.R;
import br.unaerp.compras.br.unaerp.compras.forms.FormularioProduto;
import br.unaerp.compras.br.unaerp.compras.model.ProdutoModel;

/**
 * Created by raul on 17/6/2016.
 */
public class ProdutoHelper {

    private final Spinner campoFornecedor;
    private final EditText campoCodigo;
    private final EditText campoMarca;
    private final EditText campoDescricao;
    private final Spinner campoTamanho;

    private ProdutoModel produto;

    public ProdutoHelper(FormularioProduto activity) {

        campoFornecedor = (Spinner) activity.findViewById(R.id.form_produto_fornecedor);
        campoCodigo = (EditText) activity.findViewById(R.id.form_produto_cod);
        campoMarca = (EditText) activity.findViewById(R.id.form_produto_marca);
        campoDescricao = (EditText) activity.findViewById(R.id.form_produto_descr);
        campoTamanho = (Spinner) activity.findViewById(R.id.form_produto_tamanho);

        produto = new ProdutoModel();
    }

    public ProdutoModel getProduto() {
        produto.setFornecedor(campoFornecedor.getSelectedItem().toString());
        produto.setCodigo(campoCodigo.getText().toString());
        produto.setMarca(campoMarca.getText().toString());
        produto.setDescricao(campoDescricao.getText().toString());
        produto.setTamanho(campoTamanho.getSelectedItem().toString());

        return produto;
    }

    public void preencheFormulario(ProdutoModel produto, ArrayAdapter<CharSequence> tamanho, ArrayAdapter<String> fornecedor) {
        campoFornecedor.setSelection(fornecedor.getPosition(produto.getFornecedor()));
        campoCodigo.setText(produto.getCodigo());
        campoMarca.setText(produto.getMarca());
        campoDescricao.setText(produto.getDescricao());
        campoTamanho.setSelection(tamanho.getPosition(produto.getTamanho()));
        this.produto = produto;
    }

    public String validaFormulario() {
        if (campoCodigo.getText().toString() == null || campoCodigo.getText().toString().isEmpty()) {
            return "Código do produto";
        } else if (campoDescricao.getText().toString() == null || campoDescricao.getText().toString().isEmpty()) {
            return "descrição do produto";
        } else if (campoMarca.getText().toString() == null || campoMarca.getText().toString().isEmpty()) {
            return "marca do produto";
        }

        return "sucesso";
    }


}
