package com.vald3nir.toolkit.designsystem.theme.domain

//enum class ThemeBrandEnum {
//    DEFAULT,
//    ANDROID,
//}

enum class ThemeBrandEnum2(private val label: String) {
    BLUE("Azul"),
    GREEN("Verde"),
    PURPLE("Roxo"),
    RED("Vermelho"),
    YELLOW("Amarelo");

    val key: String
        get() = label

    companion object {

        fun fromKey(key: String?): ThemeBrandEnum2? {
            return key
                ?.lowercase()
                ?.let { value -> ThemeBrandEnum2.entries.firstOrNull { it.label.lowercase() == value } }
        }
    }
}