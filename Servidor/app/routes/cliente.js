module.exports = function (app) {
    let api = app.api.cliente;

    app.all('*', function (req, res, next) {
        res.header('Access-Control-Allow-Origin', '*');
        res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
        res.header('Access-Control-Allow-Headers', 'Content-Type');
        next();
    });

    app.route('/v1/clientes')
        //.get(api.lista)
        .post(api.adiciona);

    app.route('/v1/clientes/:id')
        .get(api.buscaPorId)
        .delete(api.removePorId)
        .put(api.atualiza);


};

