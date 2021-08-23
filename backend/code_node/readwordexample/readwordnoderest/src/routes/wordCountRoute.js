module.exports = (app)=> {
    var wordCountController = require('../controller/wordCountController')
    app.route('/api/wordcountexample')
      .get(wordCountController.wordCountExample)
  }