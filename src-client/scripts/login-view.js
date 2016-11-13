const React = require('react')
const ACTIONS = require('./actions.js')


const AuthView = React.createClass ({
   _handleLogin: function(evt){
      // console.log(this.refs, "push");

      evt.preventDefault()

      let newUserData = {
         name: this.refs.name.value,
         password: this.refs.password.value
      }

      // console.log(newUserData);

      ACTIONS.authenticateUser(newUserData)
   },

     render: function (){
       return(
         <div>
            <h2>SUDS Login Page</h2>
            <form className="input-group">
               <input type="text" ref="name" className="form-control" placeholder="Drinker Name" aria-describedby="basic-addon1"/>
               <input type="text" ref="password" className="form-control" placeholder="Drinker Password" aria-describedby="basic-addon1"/>

            <div className="btn-group" role="group" aria-label="...">
               <button type="button" className="btn btn-default" onClick={this._handleLogin}>Submit</button>
            </div>
            </form>
         </div>
       )
     }
})

module.exports = AuthView
