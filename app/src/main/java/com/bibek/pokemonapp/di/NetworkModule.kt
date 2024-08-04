package com.bibek.pokemonapp.di
import android.content.Context
import com.bibek.pokemonapp.utils.NetworkLogger
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserver
import com.bibek.pokemonapp.utils.connectivity.ConnectivityObserverImpl
import com.bibek.pokemonapp.utils.connectivity.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json, json = Json {
                    ignoreUnknownKeys = true
                })
                json(contentType = ContentType.Application.FormUrlEncoded)
                json(contentType = ContentType.Text.Plain)
            }
            expectSuccess = true
            install(HttpTimeout) {
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                accept(ContentType.Text.Plain)
            }
            install(Logging) {
                logger = NetworkLogger()
                level = LogLevel.ALL
            }
        }
    }
    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserverImpl(context.connectivityManager)
    }
}