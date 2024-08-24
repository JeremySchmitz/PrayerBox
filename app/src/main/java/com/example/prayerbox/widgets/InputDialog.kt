package com.example.prayerbox.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun InputDialog(
    modifier: Modifier = Modifier,
    dialogTitle: String,
    dialogText: String = "",
    onDismissRequest: () -> Unit,
    content: @Composable() () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dialogTitle,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )

                if (dialogText.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = dialogText,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }

                content()

            }
        }

    }
}

@Preview
@Composable
fun InputDialogPreview() {
    InputDialog(dialogTitle = "My Title", dialogText = "My Dialog Text", onDismissRequest = {}) {
        Text(text = "MyText")
    }

}