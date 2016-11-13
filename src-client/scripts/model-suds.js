const Backbone = require ('backbone')

const SudsModel = Backbone.Model.extend({
  url: "/suds",

  initialize: function (){


  }
})

const SudsCollection = Backbone.Collection.extend({
  model: SudsModel,
  url: "/suds",

  initialize: function(){


  }
})

module.exports = {
  SudsModel,
  SudsCollection
}
