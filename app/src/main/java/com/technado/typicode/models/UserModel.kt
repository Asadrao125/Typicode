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
)

class Company(
    var name: String?,
    var catchPhrase: String?,
    var bs: String?
)

class Address(
    var street: String?,
    var suite: String?,
    var city: String?,
    var zipcode: String?,
    var geo: Geo?
)

class Geo(
    var lat: String?,
    var lng: String?
)