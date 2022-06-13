package com.example.mountaintravel.main

data class User (
    val id: String,
    val email: String,
    val username: String?,
    val phoneNumber: String?,
    val alamat: String?
){
    constructor():this("","","","","")
}