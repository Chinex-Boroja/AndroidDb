package com.ihediohachinedu.accessimageapp.global

import com.ihediohachinedu.accessimageapp.preference.PreferenceImpl
import com.ihediohachinedu.accessimageapp.preference.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindsPreferenceStorage(preferenceImpl: PreferenceImpl): PreferenceStorage
}