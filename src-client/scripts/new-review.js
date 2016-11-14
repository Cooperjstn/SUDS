const React = require('react');
const ACTIONS = require('./actions.js')


const NewSudsRview = React.createClass ({
   _handleNewBrew: function(evt){
     let SudsItemObj = {
        name: this.refs.name.value,
        filename: this.refs.img.value,
        brewery: this.refs.brewery.value,
        description: this.refs.des.value,
        rating: parseInt(this.refs.rating.value,10),
        category: this.refs.cat.value

     }
     console.log(SudsItemObj);


     ACTIONS.createNewSuds(SudsItemObj)
  },


   render : function (){
     return (
      <div>
         <div className="input-group">
            <input type="text" ref="name" className="form-control" placeholder="Beer Name" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" ref="cat" className="form-control" placeholder="Beer Category" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" ref="des" className="form-control" placeholder="Description" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" ref="brewery" className="form-control" placeholder="Brewery" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" ref="rating" className="form-control" placeholder="Rating" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" ref="img" className="form-control" placeholder="Img" aria-describedby="basic-addon1"/>
         </div>

         <div className="btn-group" role="group" aria-label="...">
            <button type="button" className="btn btn-default" onClick= {this._handleNewBrew}>Submit</button>
         </div>
      </div>
     )
   }
})

module.exports = NewSudsRview
