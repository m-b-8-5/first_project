package com.mb.myapplication.ui.nfc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun NfcScreen(nfc1: String, nfc2: String, nfc3: String, nfc4: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = nfc1
        )
        Text(
            text = nfc2
        )
        Text(
            text = nfc3
        )
        Text(
            text = nfc4
        )
    }
}