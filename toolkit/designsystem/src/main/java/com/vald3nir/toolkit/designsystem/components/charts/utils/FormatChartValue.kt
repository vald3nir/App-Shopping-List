package com.vald3nir.toolkit.designsystem.components.charts.utils

import java.util.Locale

fun formatChartValue(value: Float): String {
    return when {
        value >= 1_000_000f -> String.format(Locale.getDefault(), "%.0fM", value / 1_000_000f)
        value >= 1_000f -> String.format(Locale.getDefault(), "%.0fK", value / 1_000f)
        else -> String.format(Locale.getDefault(), "%.0f", value)
    }
}