package br.com.fiap.recipesfiap.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_items")
data class StockItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val category: String = "",
    val quantity: Int = 0,
    @ColumnInfo(name = "min_quantity") val minQuantity: Int = 0,
    val unit: String = "",
    val status: String = "OK",
    @ColumnInfo(name = "expiry_date") val expiryDate: String = ""
)
