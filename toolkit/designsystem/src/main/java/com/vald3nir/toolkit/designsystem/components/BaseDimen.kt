package com.vald3nir.toolkit.designsystem.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val ToolkitSpacingXs = 4.dp
val ToolkitSpacingSm = 8.dp
val ToolkitSpacingMd = 16.dp
val ToolkitSpacingLg = 24.dp
val ToolkitSpacingXl = 32.dp

@Composable
fun ToolkitSpaceHeight(height: Dp = ToolkitSpacingMd) = Spacer(modifier = Modifier.height(height))

@Composable
fun ToolkitSpaceWidth(width: Dp = ToolkitSpacingMd) = Spacer(modifier = Modifier.width(width))

// todo remover dessa linha para baixo

val defaultSpace = 16.dp
val halfSpace = defaultSpace / 2
val minSpace = halfSpace / 2
val bigSpace = defaultSpace * 4

@Composable
fun DefaultSpaceHeight() = Spacer(modifier = Modifier.height(defaultSpace))

@Composable
fun DefaultSpaceWidth() = Spacer(modifier = Modifier.width(defaultSpace))

@Composable
fun HalfSpaceWidth() = Spacer(modifier = Modifier.width(halfSpace))

@Composable
fun BigSpaceHeight() = Spacer(modifier = Modifier.height(bigSpace))

@Composable
fun MinSpaceHeight() = Spacer(modifier = Modifier.height(minSpace))