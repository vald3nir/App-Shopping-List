plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
}

android {
    namespace = "com.vald3nir.shoppinglist.core"
    defaultConfig {
        buildConfigField("int", "DB_VERSION", 1.toString())
    }
}

dependencies {
    api(project(":toolkit:core"))
    api(project(":toolkit:designsystem"))
    api(project(":toolkit:libs:auth"))
    api(project(":toolkit:libs:themas"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}