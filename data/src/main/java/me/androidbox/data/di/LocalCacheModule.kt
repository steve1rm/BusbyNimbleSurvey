package me.androidbox.data.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localCacheModule = module {

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "secret_shared_prefs",
            MasterKey(androidApplication())
        )
    }
}