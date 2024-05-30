package com.mb.myapplication.ui.top

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mb.myapplication.R
import com.mb.myapplication.app.BaseAppColor
import com.mb.myapplication.app.BgInputFieldColor
import com.mb.myapplication.app.White
import com.mb.myapplication.ui.dialogs.MyDatePicker
import com.mb.myapplication.utils.Const
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TopScreen() {
    Surface(
        modifier = Modifier
            .padding(30.dp)
    ) {
        InputFiled()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputFiled() {
    val context = LocalContext.current
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier.background(Color.White),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
        ) {
            val whatText = remember { mutableStateOf("") }
            val whenText = remember { mutableStateOf("") }
            val howMuchText = remember { mutableStateOf("") }

            // when Input Field
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.input_when_title),
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(
                    onClick = {
                        showDatePicker(whenText.value, context) { whenText.value = it }
                    },
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(3.dp))
                        .background(BgInputFieldColor),
                ) {
                    Text(
                        text = whenText.value,
                        textAlign = TextAlign.Center,
                        color = BaseAppColor,
                        fontSize = 18.sp
                    )
                }
            }

            // for what Input Field
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.input_for_what_title),
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                TextField(
                    value = whatText.value,
                    textStyle = TextStyle(
                        color = BaseAppColor,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    onValueChange = { whatText.value = it },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = BgInputFieldColor,
                        focusedContainerColor = BgInputFieldColor,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        localSoftwareKeyboardController?.hide()
                    }),
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .onFocusChanged { focusState ->
                            if (!focusState.isFocused) {
                                localSoftwareKeyboardController?.hide()
                            }
                        }
                )
            }

            // how much Input Field
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.input_how_much_title),
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(
                    onClick = {
                        showDatePicker(howMuchText.value, context) { howMuchText.value = it }
                    },
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(3.dp))
                        .background(BgInputFieldColor),
                ) {
                    Text(
                        text = howMuchText.value,
                        textAlign = TextAlign.Center,
                        color = BaseAppColor,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
fun showDatePicker(selectedDate: String = "", context: Context, onDecideDate: (String) -> Unit){
    val locale = Locale("en", "US", "US")
    val currentCal = Calendar.getInstance(locale)
    val month = if (selectedDate.isNotEmpty()) selectedDate.split(" ")[0].toInt() else currentCal.get(Calendar.MONTH)
    val day = if (selectedDate.isNotEmpty()) selectedDate.split(" ")[1].split(",")[0].toInt() else currentCal.get(Calendar.DAY_OF_MONTH)
    val year = if (selectedDate.isNotEmpty()) selectedDate.split(" ").last().toInt() else currentCal.get(Calendar.YEAR)
    currentCal.time = Date()
    currentCal.set(year, month, day)

    DatePickerDialog(
        context,
        { _, pickedYear, pickedMonth, pickedDay ->
            onDecideDate("${pickedMonth + 1} $pickedDay, $pickedYear")
        }, year, month, day
    ).show()
}