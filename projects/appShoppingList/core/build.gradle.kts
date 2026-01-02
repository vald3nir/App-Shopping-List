import com.toolkit.plugs.AppEnvironmentParameters

plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.network)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.toolkit.supabase)
}

val parameters = AppEnvironmentParameters.from(envFilePath = "D:\\Documents\\GitHub\\environments\\app-shopping-list\\param.env")

android {
    namespace = "com.vald3nir.shoppinglist.core"
    defaultConfig {
        buildConfigField("int", "DB_VERSION", 1.toString())
        buildConfigField("String", "SUPABASE_URL", parameters.supabaseUrl)
        buildConfigField("String", "SUPABASE_KEY", parameters.supabaseKey)
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