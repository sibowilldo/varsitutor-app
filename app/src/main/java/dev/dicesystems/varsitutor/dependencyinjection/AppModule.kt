package dev.dicesystems.varsitutor.dependencyinjection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dicesystems.varsitutor.data.local.AppDatabase
import dev.dicesystems.varsitutor.data.local.dao.UserDao
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.repository.MainRepositoryImpl
import dev.dicesystems.varsitutor.util.AuthInterceptor
import dev.dicesystems.varsitutor.util.Constants.BASE_URL
import dev.dicesystems.varsitutor.util.PreferenceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }
    @Singleton
    @Provides
    fun provideAppRepository(
        api: ApiServiceInterface,
        userDao: UserDao
    ) = MainRepositoryImpl(api, userDao)

    @Provides
    @Singleton
    fun providesPreferenceManager(context: Context): PreferenceManager{
        return PreferenceManager(context)
    }

    @Provides
    @Singleton
    fun providesAuthInterceptor(preferenceManager: PreferenceManager): AuthInterceptor{
        return AuthInterceptor(preferenceManager)
    }

    @Singleton
    @Provides
    fun provideApi(context: Context): ApiServiceInterface {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(preferenceManager = PreferenceManager(context)))
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL)
            .build()
            .create(ApiServiceInterface::class.java)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "varsitutor-db"
        ).build()
    }
}