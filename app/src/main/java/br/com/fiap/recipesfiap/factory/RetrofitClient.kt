package br.com.fiap.recipesfiap.factory

import br.com.fiap.recipesfiap.service.CategoryService
import br.com.fiap.recipesfiap.service.RecipeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "http://10.0.2.2:8080/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Obter uma instância, gerada pelo Retrofit, de um objeto CategoryService
    fun getCategoryService(): CategoryService {
        return retrofit.create(CategoryService::class.java);
    }

    // Obter uma instância, gerada pelo Retrofit, de um objeto RecipeService
    fun getRecipeService(): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

}