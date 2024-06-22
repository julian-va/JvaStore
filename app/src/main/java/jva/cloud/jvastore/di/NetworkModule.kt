package jva.cloud.jvastore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jva.cloud.jvastore.data.remote.repository.ProductsRepository
import jva.cloud.jvastore.util.ConstantApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides()
    @Singleton
    fun providesRetrofitBackend(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantApp.BASE_URL_BACKEND)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsRepository(retrofit: Retrofit): ProductsRepository {
        return retrofit.create(ProductsRepository::class.java)
    }
}