package br.com.fiap.recipesfiap.model

data class AlertItem(
    val id: Int,
    val title: String,
    val description: String,
    val timeAgo: String,
    val severity: String = "INFO"
)
