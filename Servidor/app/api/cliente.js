let api = {};

let contador = 2;
let clientes = [
    {
        _id: 1,
        nome: 'Raul Felipe',
        sobrenome: 'de Melo',
        apelido: '',
        telefone: '',
        celular: '(16) 99174-9537',
        email: 'raul@email.com.br'
    },
    {
        _id: 2,
        nome: 'Camila',
        sobrenome: 'Sperling',
        apelido: 'Mila',
        telefone: '',
        celular: '(16) 99574-9533',
        email: 'camila@email.com.br'
    }];

api.lista = function (req, res) {
    res.json(clientes);
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
    res.setHeader('Access-Control-Allow-Origin', '*');
    console.log(req);
    let cliente = req.body; 
    cliente._id =  ++contador;
    clientes.push(cliente);
    res.json(req.body)
};

api.atualiza = function(req,res){
    var cliente = req.body;
    var clienteId = req.params.id;
    
    var indice = clientes.findIndex(function(cliente){
        return cliente._id == clienteId;
    });
    
    clientes[indice] = cliente;
    
    res.sendStatus(200);
};
module.exports = api;