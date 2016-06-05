let mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/compras');

mongoose.connection.on('connected', function () {
    console.log('conectado ao db');
});

mongoose.connection.on('error', function (error) {
    console.log("Erro:10 - "+error);
});

mongoose.connection.on('disconnected', function () {
    console.log('Desconectado do Mongo');
});

process.on('SIGINT', function () {
    mongoose.connection.close(function () {
        console.log('Conexão encerrada pelo término da aplicação');
        process.exit(0);
    });
});