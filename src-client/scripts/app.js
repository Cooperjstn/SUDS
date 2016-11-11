const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');


const AppRouter = Backbone.Router.extend ({
  routes: {
    "/singleview" : "renderSingleView",
    "/input" : "renderSUDSInput",
    "/suds"  : "renderMultiSUDSView",
<<<<<<< HEAD
    "/user" : "renderCreateUserView"
=======
    "/user" : "renderCreateUserView",
>>>>>>> 73214147b3b76fc035c4d8afdb3f9691b7fd8c4b
    "/login" : 'renderAuthView'
  },

renderSingleView: function(){
  ReactDOM.render(<AppViewController routedFrom="SingleView"/>, document.querySelector('app-container'))
},

renderSUDSinput: function(){
  ReactDOM.render(<AppViewController routedFrom="SUDSInput"/>, document.querySelector('app-container'))
},

renderMultiSUDSView: function (){
  ReactDOM.render(<AppViewController routedFrom="MultiSUDSView"/>, document.querySelector('app-container'))
},

renderCreateUserView: function (){
  ReactDOM.render(<AppViewController routedFrom="CreateUserView"/>, document.querySelector('app-container'))
},

renderAuthView: function (){
  ReactDOM.render(<AppViewController routedFrom="AuthView"/>, document.querySelector('app-container'))
},

initialize: function(){
  Backbone.history.start();
}


})





document.querySelector('#app-container').innerHTML = `<h1>Yah okay</h1>`

let app = new AppRouter()
