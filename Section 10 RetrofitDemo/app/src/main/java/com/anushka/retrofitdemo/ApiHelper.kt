package com.anushka.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class ApiHelper {

    companion object{

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun getAlbumServices(): AlbumServices{
            return getRetrofitInstance().create(AlbumServices::class.java)
        }

    }


}

interface AlbumServices{

    @GET("albums")
    suspend fun getAlbums(): Response<Album>

    @GET("albums")
    suspend fun getSortedAlbums(@Query("userId")userId:Int): Response<Album>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id")id:Int): Response<AlbumItem>

    @POST("albums")
    suspend fun uploadAlbum(@Body albumItem: AlbumItem): Response<AlbumItem>

}