package jva.cloud.jvastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jva.cloud.jvastore.data.local.repository.CategoryLocalRepository
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.data.local.repository.impl.CategoryLocalRepositoryImpl
import jva.cloud.jvastore.data.local.repository.impl.ProductsLocalRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryLocalModule {
    @Binds
    abstract fun provideProductsLocalRepository(impl: ProductsLocalRepositoryImpl): ProductsLocalRepository

    @Binds
    abstract fun provideCategoryLocalRepository(impl: CategoryLocalRepositoryImpl): CategoryLocalRepository
}