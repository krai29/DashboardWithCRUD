package com.krai29.dashboardwithcrud.data

import java.io.Serializable

data class Person(
    var name : String,
    var description : String,
    var personImageUrl : String) : Serializable