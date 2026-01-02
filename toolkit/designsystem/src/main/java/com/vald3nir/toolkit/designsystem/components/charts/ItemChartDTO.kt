package com.vald3nir.toolkit.designsystem.components.charts

data class ItemChartDTO(val value: Float, val label: String)

fun List<ItemChartDTO>.sum(): Float = sumOf { it.value.toDouble() }.toFloat()
