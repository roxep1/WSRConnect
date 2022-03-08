package com.bashkir.wsrconnect

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    factory {params -> ConnectViewModel(params.get(), androidApplication()) }
}