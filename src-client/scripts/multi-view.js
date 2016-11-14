const React = require('react')
const ACTIONS = require('./actions.js')

const MultiSudsView = React.createClass ({
  componentWillMount: function(){
    ACTIONS.fetchSudsCollection()
  },
  render: function(){
      let beerArray = this.props.sudsList.map(function(model, index){
        return <div>
        <div className = "row">

           <div className = "col-sm-6 col-md-3">
              <a className = "thumbnail">
                 <img src = {model.get("filename") }/ >
                 <h4> {model.get("name")} </h4>
                       <p> {model.get("brewery")}</p>
                       <p> {model.get("description")}</p>
                       <p> {model.get("rating")}</p>
                       <p> {model.get("category")}</p>

              </a>
           </div>
           </div>
           </div>
         })
      //     <img src= {model.get("filename")}/>
      //     <h4> {model.get("name")} </h4>
      //           <p> {model.get("brewery")}</p>
      //           <p> {model.get("description")}</p>
      //           <p> {model.get("rating")}</p>
      //           <p> {model.get("category")}</p>
      //           </div>
      // })
      //

      return(
           <div className="container">
            <div className="left buttons">
            <a href="#input" onClick={this.handleClick}>
              <button type="button" className="btn btn-default" >Input</button>
              </a>
              <a href="#login" onClick={this.handleClick}>
                <button type="button" className="btn btn-default" >Logout</button>
                </a>
              <div className="col-xs-6 col-md-4">
                 <div className="tile sm-1-x-1 bg-info">
                    <div className="tile-content contents-centered">
                       <h3>Beer</h3>
                       {beerArray}
                    </div>
                 </div>
              </div>
            </div>
            </div>


      )
    }
    })




module.exports = MultiSudsView
