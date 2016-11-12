const React = require('react')
const STORE = require('./store.js')
const ACTIONS = require('./actions.js')

// const MultiSudsView = require('./multi-suds-view.js')
const LoginView = require('./login-view.js')
const NewSudsRview = require('./new-review.js')

const AppViewController = React.createClass({
  getInitialState: function(){
    STORE.setStore('currentSuds', [])
    let startingState = this.getStoreData()
    return startingState
},

 componentWillMount: function(){
    let self = this
    STORE.onChange(function(){
        let updateState = STORE.getStoreData()
        self.setState(updateState)
    })
},

  render: function(){
    switch(this.props.routedFrom){
      case "LoginView":
         return <LoginView/>
         break;

      // case "NewSudsView":
      //    return <NewSudsView currentTodos={this.state.currentTodos} />
      //    break;

      case "NewSudsRview":
         return <NewSudsRview/>
         break;


      case "MultiSudsView":
         console.log("rendering dashboard(multiview)")
         return <MultiSudsView/>
         break;

      default:
         return <div><h1>Yolo!!</h1></div>
    }
  }

})

module.exports = AppViewController
