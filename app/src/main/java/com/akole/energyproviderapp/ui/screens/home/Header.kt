package com.akole.energyproviderapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.akole.energyproviderapp.R

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        val painter = rememberAsyncImagePainter(R.drawable.logo)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
