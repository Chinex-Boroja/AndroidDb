package com.ihediohachinedu.accessimageapp.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    fun savedKey() : Flow<Boolean>
    suspend fun setSavedKey(order: Boolean)
}