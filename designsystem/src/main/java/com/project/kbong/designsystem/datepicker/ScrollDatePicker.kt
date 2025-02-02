package com.project.kbong.designsystem.datepicker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.utils.DateUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScrollDatePicker() {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        YearDateContent(
            coroutineScope = coroutineScope
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YearDateContent(
    coroutineScope: CoroutineScope,
    yearRange: List<Int> = (1940..2100).toList()
) {
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val year = DateUtil.getYearInt() - 1
    val startDate = yearRange.indexOf(year)
    val isScrollInProgress = lazyListState.isScrollInProgress

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            lazyListState.scrollToItem(startDate)
        }
    }

    LaunchedEffect(key1 = isScrollInProgress) {

    }

    LazyColumn(
        modifier = Modifier.height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
        flingBehavior = flingBehavior
    ) {
        items(yearRange) { idx ->
            Text(
                text = "${idx}"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewScrollDatePicker() {
    val coroutineScope = rememberCoroutineScope()

    YearDateContent(
        coroutineScope = coroutineScope
    )
}
