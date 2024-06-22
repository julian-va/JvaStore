package jva.cloud.jvastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jva.cloud.jvastore.domain.usecase.GetAllProducts
import jva.cloud.jvastore.domain.usecase.RetrieveProductById
import jva.cloud.jvastore.domain.usecase.impl.GetAllProductsImpl
import jva.cloud.jvastore.domain.usecase.impl.RetrieveProductByIdImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideGetAllProducts(impl: GetAllProductsImpl): GetAllProducts

    @Binds
    abstract fun provideRetrieveProductById(impl: RetrieveProductByIdImpl): RetrieveProductById
}