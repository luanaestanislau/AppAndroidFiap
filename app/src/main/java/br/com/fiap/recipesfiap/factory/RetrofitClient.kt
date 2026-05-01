package br.com.fiap.recipesfiap.factory

import br.com.fiap.recipesfiap.service.CategoryService
import br.com.fiap.recipesfiap.service.RecipeService
import br.com.fiap.recipesfiap.service.StockService
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

    fun getCategoryService(): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    fun getRecipeService(): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    fun getStockService(): StockService {
        return retrofit.create(StockService::class.java)
    }
}