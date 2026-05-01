package br.com.fiap.recipesfiap.repository

import br.com.fiap.recipesfiap.dao.StockItemDao
import br.com.fiap.recipesfiap.model.StockItem

class StockRepository(private val dao: StockItemDao) {
    fun getAllItems(): List<StockItem> = dao.getAllItems()
    fun insert(item: StockItem) = dao.insert(item)
    fun delete(item: StockItem) = dao.delete(item)
}
