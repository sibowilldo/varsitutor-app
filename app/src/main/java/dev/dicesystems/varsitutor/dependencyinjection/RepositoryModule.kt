package dev.dicesystems.varsitutor.dependencyinjection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.dicesystems.varsitutor.repository.MainRepository
import dev.dicesystems.varsitutor.repository.MainRepositoryImpl


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideMainRepositoryImpl(repository: MainRepositoryImpl): MainRepository
}