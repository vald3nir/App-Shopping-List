plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
}
android {
    namespace = "com.vald3nir.toolkit.auth"
}

dependencies {
    implementation(project(":toolkit:core"))
    implementation(project(":toolkit:designsystem"))
}