const React = require('react');


const NewSudsRview = React.createClass ({
   render : function (){
     return (
      <div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Beer Name" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Beer Category" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Description" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Brewery" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Rating" aria-describedby="basic-addon1"/>
         </div>
         <div className="input-group">
            <input type="text" className="form-control" placeholder="Img" aria-describedby="basic-addon1"/>
         </div>

         <div className="btn-group" role="group" aria-label="...">
            <button type="button" className="btn btn-default">Submit</button>
         </div>
      </div>
     )
   }
})

module.exports = NewSudsRview
