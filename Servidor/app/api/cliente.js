let api = {};

let contador = 2;
let clientes = [{ "_id": 1, "nome": "Raul", "sobrenome": "De melo", "apelido": "Heuhe", "telefone": "", "celular": "16 9999-9999", "email": "hue@hotmail.com" }];

api.lista = function (req, res) {
    res.json(clientes);
    console.log("lista");
};

api.buscaPorId = function (req, res) {
    let id = req.params.id;
    let cliente = clientes.find(function (cliente) {
        return cliente._id == id;
    });

    if (!cliente) {
        res.end("Cliente nao encontrado");
    }
    res.json(cliente);

};

api.removePorId = function (req, res) {

    clientes = clientes.filter(function (cliente) {
        return cliente._id != req.params.id;
    });

    res.sendStatus(204);
};

api.adiciona = function (req, res) {
        
    console.log(req);
    let cliente = req.body;
    //cliente._id =  ++contador;
    clientes.push(cliente);
    res.sendStatus(204);
    console.log("Adiciona");
};

api.atualiza = function (req, res) {
    var cliente = req.body;
    var clienteId = req.params.id;

    var indice = clientes.findIndex(function (cliente) {
        return cliente._id == clienteId;
    });

    clientes[indice] = cliente;

    res.sendStatus(200);
};
module.exports = api;