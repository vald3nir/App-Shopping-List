plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
}
android {
    namespace = "com.vald3nir.toolkit.themas"
}

dependencies {
    implementation(project(":toolkit:core"))
    implementation(project(":toolkit:designsystem"))
}