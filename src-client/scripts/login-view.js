const React = require('react')

const AuthView = React.createClass ({
     render: function (){
       return(
         <div>
            <h2>SUDS Login Page</h2>
            <div className="input-group">
               <input type="text" className="form-control" placeholder="Drinker Name" aria-describedby="basic-addon1"/>
               <input type="text" className="form-control" placeholder="Drinker Login" aria-describedby="basic-addon1"/>
            </div>
         </div>
       )
     }
})

module.exports = AuthView
