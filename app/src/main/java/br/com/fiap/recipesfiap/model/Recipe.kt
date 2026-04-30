package br.com.fiap.recipesfiap.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Recipe(
    val id: Int = 0,
    val category: Category,
    val name: String = "",
    val description: String = "",
    val user: User,
    val difficultLevel: DifficultLevel,
    val cookingTime: Int = 0,
    val createdAt: LocalDate = LocalDate.now(),
    @DrawableRes val image: Int
)
