plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
}

android {
    namespace = "com.vald3nir.toolkit.core"
}

dependencies {
}