package br.com.fiap.recipesfiap.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.recipesfiap.model.StockItem

@Dao
interface StockItemDao {
    @Query("SELECT * FROM stock_items")
    fun getAllItems(): List<StockItem>

    @Insert
    fun insert(item: StockItem)

    @Delete
    fun delete(item: StockItem)
}
