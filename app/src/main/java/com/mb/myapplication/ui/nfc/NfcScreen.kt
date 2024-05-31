package com.mb.myapplication.ui.nfc

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun NfcScreen(nfc1: String, results: List<String>) {
    ConstraintLayout(
        Modifier.fillMaxSize()
    ) {
        val (nfcText, resultTexts) = createRefs()
        val centerGuideline = createGuidelineFromTop(0.5f)
        Text(
            modifier = Modifier
                .constrainAs(nfcText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(centerGuideline)
            },
            text = nfc1
        )

        val tests = mutableListOf<String>()
        for (i in 0..30) {
            tests.add("test $i")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(resultTexts) {
                    top.linkTo(centerGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)

        ) {
            items(tests.size, key = { it }) { iteration ->
                Text(
                    text = tests[iteration],
                    modifier = Modifier.padding(8.dp)
                )
                val listState = rememberLazyListState()
                Log.d("mibo", "NfcScreen: ${listState.layoutInfo.totalItemsCount}")
            }
        }
    }
}