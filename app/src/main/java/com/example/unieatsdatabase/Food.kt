package com.example.unieatsdatabase

class Food(val id: String?, val name: String?, val calories: Int?, val gramsCarbs: Int?, val gramsFats: Int?,
           val gramsProteins: Int?, val image: String?) {
    constructor() : this("","", 0, 0, 0, 0, ""){

    }
}