package br.com.fiap.recipesfiap.model

data class Delivery(
    val id: Int,
    val orderNumber: String,
    val supplier: String,
    val eta: String,
    val status: String = "IN_ROUTE"
)
