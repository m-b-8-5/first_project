package com.mb.myapplication.ui.inputList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mb.myapplication.app.BaseAppColor
import com.mb.myapplication.app.BgInputFieldColor
import com.mb.myapplication.data.model.InputData

@Composable
fun InputListScreen() {
}

@Composable
fun InputsRow(inputs: InputData, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = inputs.howMuch,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp),
            color = BaseAppColor
        )

        Text(
            text = inputs.date,
            style = MaterialTheme.typography.titleSmall,
            color = BgInputFieldColor
        )
    }

}

@Preview
@Composable
fun InputsRowPreview() {
   InputsRow(
       inputs = InputData(1, "14.05 2024", "555 yen", "Food")
   )
}