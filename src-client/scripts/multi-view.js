const React = require('react')
const ACTIONS = require('./actions.js')

const MultiSudsView = React.createClass ({
  componentWillMount: function(){
    ACTIONS.fetchSudsCollection()
  },
  render: function(){
      let beerArray = this.props.sudsList.map(function(model, index){
        return <h4> {model.get('name')} </h4>
      })


      return(
           <div className="container">
              <div className="col-xs-6 col-md-4">
                 <div className="tile sm-1-x-1 bg-info">
                    <div className="tile-content contents-centered">
                       <h3>Get Your LIst</h3>
                       {beerArray}
                    </div>
                 </div>
              </div>
            </div>

      )
    }
    })




module.exports = MultiSudsView
