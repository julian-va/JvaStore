package jva.cloud.jvastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jva.cloud.jvastore.domain.usecase.GetAllProducts
import jva.cloud.jvastore.domain.usecase.RetrieveProductById
import jva.cloud.jvastore.domain.usecase.RetrieveProductFromLocalById
import jva.cloud.jvastore.domain.usecase.RetrieveProductsFromLocal
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.domain.usecase.impl.GetAllProductsImpl
import jva.cloud.jvastore.domain.usecase.impl.RetrieveProductByIdImpl
import jva.cloud.jvastore.domain.usecase.impl.RetrieveProductFromLocalByIdImpl
import jva.cloud.jvastore.domain.usecase.impl.RetrieveProductsFromLocalImpl
import jva.cloud.jvastore.domain.usecase.impl.SaveProductsLocalImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideGetAllProducts(impl: GetAllProductsImpl): GetAllProducts

    @Binds
    abstract fun provideRetrieveProductById(impl: RetrieveProductByIdImpl): RetrieveProductById

    @Binds
    abstract fun provideRetrieveProductsFromLocal(impl: RetrieveProductsFromLocalImpl): RetrieveProductsFromLocal

    @Binds
    abstract fun provideRetrieveProductFromLocalById(impl: RetrieveProductFromLocalByIdImpl): RetrieveProductFromLocalById

    @Binds
    abstract fun provideSaveProductsLocal(impl: SaveProductsLocalImpl): SaveProductsLocal
}