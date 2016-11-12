const React = require('react')

const AuthView = React.createClass ({
     render: function (){
       return(
         <div>
            <h2>SUDS Login Page</h2>
            <div class="input-group">
               <input type="text" class="form-control" placeholder="Drinker Name" aria-describedby="basic-addon1"/>
               <input type="text" class="form-control" placeholder="Drinker Login" aria-describedby="basic-addon1"/>
            </div>
         </div>
       )
     }
})

module.exports = AuthView
