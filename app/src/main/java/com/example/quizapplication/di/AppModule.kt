package com.example.quizapplication.di

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.quizapplication.data.data_source.local.QuestionsCacheDao
import com.example.quizapplication.data.data_source.remote.ApiQuiz
import com.example.quizapplication.data.repository.QuestionsRepositoryImpl
import com.example.quizapplication.data.repository.ScoreRepositoryImpl
import com.example.quizapplication.domain.repository.QuestionsRepository
import com.example.quizapplication.domain.repository.ScoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesQuestionsCacheDao(app: Application): QuestionsCacheDao{
        return Room.databaseBuilder(
            app,
            QuestionsCacheDao::class.java,
            QuestionsCacheDao.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesScoreRepository(
        @ApplicationContext context: Context
    ): ScoreRepository = ScoreRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun providesHttpClient() = HttpClient(Android) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("NetworkMessage", "log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 30_000
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Provides
    @Singleton
    fun providesApiQuizService(client: HttpClient) = ApiQuiz(client)


    @Provides
    @Singleton
    fun providesQuestionsRepository(
        db: QuestionsCacheDao,
        apiQuiz: ApiQuiz
    ): QuestionsRepository = QuestionsRepositoryImpl(db.questionCacheDao,apiQuiz)

}