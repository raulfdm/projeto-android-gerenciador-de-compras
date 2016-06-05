let express = require('express');
let app = express();
let consign = require('consign');
let bodyParser = require('body-parser');

//Middlewares
app.use(bodyParser.json());


consign({cwd: 'app'})
    .include('api')
    .then('routes')
    .into(app);

module.exports = app;

