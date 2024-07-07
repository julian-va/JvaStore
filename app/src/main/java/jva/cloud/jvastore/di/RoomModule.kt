package jva.cloud.jvastore.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jva.cloud.jvastore.data.local.dao.CategoryDao
import jva.cloud.jvastore.data.local.dao.ProductsDao
import jva.cloud.jvastore.data.local.dao.UserDao
import jva.cloud.jvastore.data.local.database.JvaStoreDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "Jva_store_dataBase"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): JvaStoreDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = JvaStoreDataBase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideProductsDao(dataBae: JvaStoreDataBase): ProductsDao {
        return dataBae.provideProductsDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(dataBae: JvaStoreDataBase): CategoryDao {
        return dataBae.provideCategoryDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(dataBae: JvaStoreDataBase): UserDao {
        return dataBae.provideUserDao()
    }
}