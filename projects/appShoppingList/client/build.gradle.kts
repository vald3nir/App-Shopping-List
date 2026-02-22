import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import com.toolkit.plugs.AppEnvironmentParameters
import com.toolkit.plugs.setupSigningConfigs

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.gms)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
    alias(libs.plugins.firebase.crashlytics)
}

// todo replace for your .env file path
val parameters = AppEnvironmentParameters.from(envFilePath = "D:\\GitHub\\environments\\shopping-list.env")


android {
    namespace = "com.vald3nir.shoppinglist"
    defaultConfig {
        applicationId = namespace
        versionCode = 11
        versionName = "2026.2.1"
        buildConfigField("String", "APP_PRIVACY_POLICY_URL", parameters.appPrivacyPolicyURL)
        buildConfigField("String", "APP_TERMS_USE_URL", parameters.termsUseURL)
        buildConfigField("String", "WEB_GOOGLE_CLIENT_ID", parameters.webGoogleClientID)
    }
    setupSigningConfigs(parameters = parameters)
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

tasks.withType<Test> {
    useJUnitPlatform()
}