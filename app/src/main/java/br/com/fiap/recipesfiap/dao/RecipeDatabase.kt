package br.com.fiap.recipesfiap.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.recipesfiap.model.MedItem
import br.com.fiap.recipesfiap.model.User

@Database(entities = [User::class, MedItem::class], version = 3)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun medItemDao(): MedItemDao

    companion object {
        private lateinit var instance: RecipeDatabase

        fun getDatabase(context: Context): RecipeDatabase {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "medistock_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(true)
                    .build()
            }
            return instance
        }
    }
}