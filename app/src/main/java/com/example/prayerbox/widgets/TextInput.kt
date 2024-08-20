package com.example.prayerbox.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    title: String = "Title",
    onTextChange: (value: String) -> Unit = {},
    height: Dp = 60.dp,
    placeholder: String = "",
    singleLine: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
) {
    val focusManager = LocalFocusManager.current

    var currentVal by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .height(height)
                    .fillMaxWidth()
                    .then(modifier),
                value = currentVal,
                onValueChange = {
                    currentVal = it
                    onTextChange(it)
                },
                placeholder = {
                    Text(text = placeholder, fontSize = 18.sp)
                },
                textStyle = TextStyle.Default.copy(fontSize = 24.sp),
                singleLine = singleLine,
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions {
                    if (imeAction == ImeAction.Done){
                        focusManager.clearFocus()
                        println("-- done")
                    } else if (imeAction == ImeAction.Next){
                        focusManager.moveFocus(FocusDirection.Next)
                        println("-- next")

                    }
                }

            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputPreview() {
    TextInput()
}


