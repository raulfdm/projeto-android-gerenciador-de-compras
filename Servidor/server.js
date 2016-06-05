var http = require('http');
var app = require('./config/express');
require('./config/database');

http.createServer(app).listen(2000,function(){
    console.log("porta 3000 na escuta")
});