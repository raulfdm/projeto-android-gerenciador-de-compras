module.exports = function (app) {
    let api = app.api.cliente;

    app.route('/v1/clientes')
        .get(api.lista)
        .post(api.adiciona);

    app.route('/v1/clientes/:id')
        .get(api.buscaPorId)
        .delete(api.removePorId)
        .put(api.atualiza);


};

