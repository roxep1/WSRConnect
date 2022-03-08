package com.bashkir.wsrconnect

import android.app.Application
import com.airbnb.mvrx.Mavericks
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WSRConnectApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        startKoin {
            androidContext(this@WSRConnectApplication)
            modules(mainModule)
        }
    }
}