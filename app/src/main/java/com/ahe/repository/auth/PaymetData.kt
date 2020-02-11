package com.ahe.repository.auth

data class PaymetData(
    val amt: String,
    val cc: String,
    val cm: String,
    val item_name: String,
    val item_number: String,
    val payment_data: String,
    val st: String,
    val tx: String
)