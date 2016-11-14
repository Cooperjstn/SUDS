const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');

const AppViewController = require('./viewcontroll.js')
const STORE = require ('./store.js')


const AppRouter = Backbone.Router.extend ({
  routes: {
    "input" : "renderSUDSInput",
    "suds"  : "renderMultiSudsView",
    "user" : "renderCreateUserView",
    "login" : 'renderAuthView'
  },

renderSingleView: function(){
  ReactDOM.render(<AppViewController routedFrom="SingleView"/>, document.querySelector('#app-container'))
},


renderSUDSInput: function (){
 ReactDOM.render(<AppViewController routedFrom="NewSudsRview"/>, document.querySelector('#app-container'))
},

renderMultiSudsView: function (){
  console.log('multiview');
  ReactDOM.render(<AppViewController routedFrom="MultiSudsView"/>, document.querySelector('#app-container'))
},

renderCreateUserView: function (){
  ReactDOM.render(<AppViewController routedFrom="CreateUserView"/>, document.querySelector('#app-container'))
},

renderAuthView: function (){
  ReactDOM.render(<AppViewController routedFrom="AuthView"/>, document.querySelector('#app-container'))
},

initialize: function(){
  Backbone.history.start();
}


})





document.querySelector('#app-container').innerHTML = `<h1>Yah okay</h1>`

let app = new AppRouter()
