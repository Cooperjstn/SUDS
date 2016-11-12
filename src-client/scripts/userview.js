const Backbone = require('backbone')

const UserModel = Backbone.Model.extend({
   url: "/user",

   initialize: function(){
     return {}

   },
   render: function (){
     return (
        <div className = "container">
          <h2>Hello</h2>
          </div>
     )
   }

 })



module.exports = UserModel
