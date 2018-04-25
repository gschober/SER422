'use strict';

const express = require('express');

const PORT = 8080;
const HOST = '0.0.0.0';

const app = express();
app.get('/lab5EC',(request,response)=>{
	response.send('Hello world\n');
});

app.listen(PORT,HOST);
console.log('Running on http://${HOST}:${PORT}');