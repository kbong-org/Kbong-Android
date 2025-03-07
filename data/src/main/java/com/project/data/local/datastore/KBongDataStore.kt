package com.project.data.local.datastore

import androidx.datastore.preferences.core.Preferences

interface KBongDataStore {
    suspend fun <T> updateData(data: T, key: Preferences.Key<String>)
    suspend fun <T> getData(key: Preferences.Key<String>, type: Class<T>): T?

}
