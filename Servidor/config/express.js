let express = require('express');
let app = express();
let consign = require('consign');
let bodyParser = require('body-parser');


//Middlewares
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});
app.all('*', function(req, res, next) {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
});
app.use(bodyParser.json());



consign({ cwd: 'app' })
    .include('api')
    .then('routes')
    .into(app);

module.exports = app;

