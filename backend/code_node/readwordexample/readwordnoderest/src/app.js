const express = require('express') 
const cors = require('cors')
const morgan = require('morgan')
//let schedule = require('node-schedule');

 
const wordCountRoute = require('./routes/wordCountRoute')  
const port= 8080
const hostname = "localhost"
// Express configuration
const app = express() 
app.use(morgan('combined'))
app.use(cors())
app.listen(port,hostname, ()=>{
    console.log(`app running in hostname ${hostname} port ${port}`)
})
wordCountRoute(app) 
var path = require('path');
global.appRoot = path.resolve(__dirname);

 