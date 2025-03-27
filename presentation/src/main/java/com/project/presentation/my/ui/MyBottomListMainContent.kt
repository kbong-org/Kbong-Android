package com.project.presentation.my.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

private const val MAX_COUNT = 3

@Composable
fun MyBottomListMainContent(
    modifier: Modifier = Modifier,
    imagePathList: List<String>
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(MAX_COUNT),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(imagePathList) { image ->
            AsyncImage(
                modifier = Modifier
                    .size(116.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = "image"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewMyBottomListMainContent() {
    MyBottomListMainContent(
        modifier = Modifier.padding(20.dp),
        imagePathList = listOf()
    )
}