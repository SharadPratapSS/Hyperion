package org.sharad.emify.core.di

import android.content.Context
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module
import org.sharad.emify.core.data.local.roomdb.getDatabaseBuilder
import org.sharad.emify.core.util.storage.SharedPrefsImplementation
import org.sharad.emify.core.util.storage.SharedPrefsInterface
import kotlin.math.sin

actual val targetModule= module {
    single { getDatabaseBuilder(get())}
    single<SharedPrefsInterface> { SharedPrefsImplementation(getOrNull<Context>()) }
    single { OkHttp.create() }

}