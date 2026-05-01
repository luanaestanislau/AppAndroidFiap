package br.com.fiap.recipesfiap.service

import br.com.fiap.recipesfiap.model.StockItem
import retrofit2.Call
import retrofit2.http.GET

interface StockService {
    @GET("stock")
    fun getStockItems(): Call<List<StockItem>>
}
