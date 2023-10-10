package com.andronity.rumahmakan.model.dummy

class VerticalModel (title:String, price: String, src:String, rating: Float) {

    var title = ""
    var src = ""
    var price = ""
    var rating = 0f

    init {
        this.title = title
        this.price = price
        this.src = src
        this.rating = rating
    }
}