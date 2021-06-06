package br.com.uniptcc.brasilqueimadas.core.network.rest.apis.inpa

import br.com.uniptcc.brasilqueimadas.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
object ApiService {

    private val gsonBuilder = GsonBuilder().serializeNulls()

    private val client = OkHttpClient.Builder()
        .readTimeout(2, TimeUnit.MINUTES)

    private fun getRetrofit(): Retrofit {
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }

        return Retrofit.Builder()
            .baseUrl("https://queimadas.dgi.inpe.br/queimadas/dados-abertos/api/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val apiRest: ApiRest = getRetrofit().create(ApiRest::class.java)

}