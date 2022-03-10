package com.bashkir.wsrconnect

import com.bashkir.wsrconnect.data.services.FirebaseService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single { FirebaseService() }
    factory {params -> ConnectViewModel(params.get(), androidApplication(), get()) }
}