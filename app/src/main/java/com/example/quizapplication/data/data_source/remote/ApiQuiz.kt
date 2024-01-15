package com.example.quizapplication.data.data_source.remote


import android.util.Log
import com.example.quizapplication.util.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ApiQuiz (
    private val client: HttpClient
) {
    suspend fun getQuestions(): List<QuestionResponse> {
        return try {
            client.get { url(Constants.BASE_URL) }
        } catch(e: RedirectResponseException) {
            Log.d("Error","1")
            Log.d("Error", e.response.status.description)
            emptyList()
        } catch(e: ClientRequestException) {
            Log.d("Error","2")
            Log.d("Error", e.response.status.description)
            emptyList()
        } catch(e: ServerResponseException) {
            Log.d("Error","3")
            Log.d("Error", e.response.status.description)
            emptyList()
        } catch(e: Exception) {
            Log.d("Error","4")
            println("Error: ${e.message}")
            emptyList()
        }
    }


}