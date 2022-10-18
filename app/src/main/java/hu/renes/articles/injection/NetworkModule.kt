package hu.renes.articles.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.renes.articles.BuildConfig
import hu.renes.articles.domain.service.ArticleService
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ŅetworkModule {

    private const val TIMEOUT_IN_SEC = 60
    private const val datePattern = "yyyy-MM-dd'T'HH:mm:ssZ"

    @Provides
    @Singleton
    fun provideArticleApp(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .connectTimeout(TIMEOUT_IN_SEC.toLong(), SECONDS)
            .readTimeout(TIMEOUT_IN_SEC.toLong(), SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(datePattern)
            .create()
    }

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = Level.BODY
        }
        return logging
    }

    @Provides
    @Singleton
    fun provideArticleService(retrofit: Retrofit): ArticleService {
        return ArticleService.Factory.createService(retrofit)
    }
}
