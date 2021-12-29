package com.technado.typicode.models

class UserModel(
    var id: Float,
    var name: String?,
    var username: String?,
    var email: String?,
    var address: Address?,
    var phone: String?,
    var website: String?,
    var company: Company?
) {

}

class Company {
    var name: String? = null
    var catchPhrase: String? = null
    var bs: String? = null
}

class Address {
    var street: String? = null
    var suite: String? = null
    var city: String? = null
    var zipcode: String? = null
    var geo: Geo? = null
}

class Geo {
    var lat: String? = null
    var lng: String? = null
}