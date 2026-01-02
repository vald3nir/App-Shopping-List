import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.toolkit.supabase)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.vald3nir.shoppinglist.manager"
    defaultConfig {
        applicationId = namespace
        versionCode = 1
        versionName = "2026.1.0"
    }
    buildTypes.configureEach {
        extensions.configure<CrashlyticsExtension> {
            mappingFileUploadEnabled = false
        }
    }
}

dependencies {
    implementation(project(":projects:appShoppingList:core"))
}

baselineProfile {
    // Don't build on every iteration of a full assemble.
    // Instead enable generation directly for the release build variant.
    automaticGenerationDuringBuild = false
    // Make use of Dex Layout Optimizations via Startup Profiles
    dexLayoutOptimization = true
}