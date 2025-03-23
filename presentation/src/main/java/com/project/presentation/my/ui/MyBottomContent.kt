package com.project.presentation.my.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val CATALOG = "CATALOG"
const val LIST = "LIST"

@Composable
fun MyBottomContent(
    isCatalogSelect: Boolean = false,
    isListSelect: Boolean = false,
    onClickViewType: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BottomHeaderContent(
            isCatalogSelect = isCatalogSelect,
            isListSelect = isListSelect,
            onClickViewType = onClickViewType
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyBottomContent() {
    MyBottomContent(
        onClickViewType = {}
    )
}