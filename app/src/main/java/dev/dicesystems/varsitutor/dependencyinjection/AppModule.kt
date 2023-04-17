package dev.dicesystems.varsitutor.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.repository.VacancyRepository
import dev.dicesystems.varsitutor.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideVacancyRepository(
        api: ApiServiceInterface
    ) = VacancyRepository(api)


    @Singleton
    @Provides
    fun provideApi(): ApiServiceInterface {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiServiceInterface::class.java)
    }
}