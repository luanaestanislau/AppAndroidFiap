package br.com.fiap.recipesfiap.service

import br.com.fiap.recipesfiap.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryService {

    // Esse método é reponsável por retornar uma categoria pelo Id
    @GET("categories/{categoryId}")
    fun getCategoryById(@Path("categoryId") categoryId: Int): Call<Category>

    // Esse método é responsável por retornar uma lista de categorias
    @GET("categories")
    fun getAllCategories(): Call<List<Category>>

}