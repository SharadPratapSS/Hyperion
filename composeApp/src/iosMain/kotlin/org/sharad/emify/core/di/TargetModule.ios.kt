package org.sharad.emify.core.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.sharad.emify.core.data.local.roomdb.getDatabaseBuilder
import org.sharad.emify.core.util.storage.SharedPrefsImplementation
import org.sharad.emify.core.util.storage.SharedPrefsInterface

actual val targetModule= module {
    single { getDatabaseBuilder() }
    single<SharedPrefsInterface> { SharedPrefsImplementation(getOrNull()) }
    single { Darwin.create() }
}