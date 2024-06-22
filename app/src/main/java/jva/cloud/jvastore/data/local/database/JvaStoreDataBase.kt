package jva.cloud.jvastore.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jva.cloud.jvastore.data.local.dao.CategoryDao
import jva.cloud.jvastore.data.local.dao.ProductsDao
import jva.cloud.jvastore.data.local.entity.CategoryEntity
import jva.cloud.jvastore.data.local.entity.ProductsEntity
import jva.cloud.jvastore.util.DateConverter

@Database(entities = [ProductsEntity::class, CategoryEntity::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class JvaStoreDataBase : RoomDatabase() {
    abstract fun provideProductsDao(): ProductsDao
    abstract fun provideCategoryDao(): CategoryDao
}