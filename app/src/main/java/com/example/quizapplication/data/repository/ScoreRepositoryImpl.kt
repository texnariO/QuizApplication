package com.example.quizapplication.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.quizapplication.domain.repository.ScoreRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ScoreRepositoryImpl(context: Context): ScoreRepository {
    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = POINTS
    )
    private val dataStore = context.userPreferencesDataStore
    companion object {
        const val POINTS = "user_login_state"
        private val POINTS_KEY = intPreferencesKey("points")
    }

    override suspend fun setPoints(points: Int) {
        dataStore.edit { preference ->
            preference[POINTS_KEY] = points
        }
    }
    override val getPoints: Flow<Int>
        get() = dataStore.data.catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preference ->
            preference[POINTS_KEY] ?: 0
        }
    override suspend fun clearPoint() {
        dataStore.edit { preferences ->
            preferences[POINTS_KEY] = 0
        }
    }
}