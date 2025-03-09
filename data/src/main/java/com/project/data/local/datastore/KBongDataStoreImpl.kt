package com.project.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.project.data.utils.fromJsonString
import com.project.data.utils.toJsonString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KBongDataStoreImpl @Inject constructor(
    context: Context,
) : KBongDataStore {
    private val datastore = context.datastore

    override suspend fun <T> updateData(data: T, key: Preferences.Key<String>) {
        val dataString = data.toJsonString()
        datastore.edit { settings ->
            settings[key] = dataString
        }
    }

    override suspend fun <T> getData(key: Preferences.Key<String>, type: Class<T>): T? {
        return datastore.data.map { preference ->
            preference[key]?.fromJsonString(type)
        }.first()
    }


}
