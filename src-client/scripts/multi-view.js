const React = require('react')

const MultiSudsView = React.createClass ({
  render: function(){
      let beerArray = this.props.collection.models.map(function(model, index){
        return <DummyComponent key={model.cid} data={model} />
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
