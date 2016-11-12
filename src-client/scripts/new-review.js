const ReactDOM = require('react-dom');
const React = require('react');
const Backbone = require('backbone');


const showNewReviewInput = React.createClass ({
  getInitalState: function (){
    return {}
  },

render : function (){
  return (
   <div>
      <h2>Pour some new SUDS</h2>
    </div>
  )
}
})

module.exports = showNewReviewInput
