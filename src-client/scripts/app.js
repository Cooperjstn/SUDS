const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');


const AppRouter = Backbone.Router.extend ({
  routes: {
    "/singleview" : "renderSingleView",
    "/input" : "renderSUDSInput",
    "/suds"  : "renderMultiSUDSView",
    "/user" : "renderCreateUserView"
    "/login" : 'renderAuthView'
  },

renderSingleView: function(){
  ReactDOM.render(<AppViewController)
}







})


document.querySelector('#app-container').innerHTML = `<h1>Yah okay</h1>`

new AppRouter()
