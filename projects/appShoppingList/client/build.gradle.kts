import com.google.firebase.appdistribution.gradle.AppDistributionExtension
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
    alias(libs.plugins.google.firebase.appdistribution)
}

val parameters = AppEnvironmentParameters.from(envFilePath = "D:\\Documents\\GitHub\\environments\\app-shopping-list\\param.env")


android {
    namespace = "com.vald3nir.shoppinglist"
    defaultConfig {
        applicationId = namespace
        versionCode = 12
        versionName = "2026.4.0"
        buildConfigField("String", "APP_PRIVACY_POLICY_URL", parameters.appPrivacyPolicyURL)
        buildConfigField("String", "APP_TERMS_USE_URL", parameters.termsUseURL)
        buildConfigField("String", "WEB_GOOGLE_CLIENT_ID", parameters.webGoogleClientID)
    }
    setupSigningConfigs(parameters = parameters)
    buildTypes.configureEach {
        extensions.configure<CrashlyticsExtension> {
            mappingFileUploadEnabled = false
        }
        if (name == "release") {
            configure<AppDistributionExtension> {
                serviceCredentialsFile = parameters.firebaseServiceCredentialsFilePath
                groups = "grupo-de-teste"
            }
        }
    }
}

dependencies {
    implementation(project(":projects:appShoppingList:core"))
}

baselineProfile {
    automaticGenerationDuringBuild = false
    dexLayoutOptimization = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("buildProdVersion") {
    dependsOn("appDistributionUploadProdRelease")
    doLast {
        println("✓ Build prod release .aab gerado e enviado para Firebase App Distribution com sucesso!")
    }
}
