package com.project.kbong.designsystem.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.kbong.designsystem.theme.KBongGrayscaleGray10
import com.project.kbong.designsystem.theme.KBongGrayscaleGray2
import com.project.kbong.designsystem.theme.KBongGrayscaleGray4
import com.project.kbong.designsystem.theme.KBongGrayscaleGray5
import com.project.kbong.designsystem.theme.KBongPrimary
import com.project.kbong.designsystem.theme.KBongTypography

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    isError: Boolean = false,
    maxTextLength: Int = 10,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(14.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val isValueEmpty = value.isEmpty()
    val isFocused = interactionSource.collectIsFocusedAsState().value

    val borderColor = when {
        isFocused -> KBongPrimary
        else -> KBongGrayscaleGray2
    }

    val textColor = when {
        isValueEmpty -> KBongGrayscaleGray5
        else -> KBongGrayscaleGray10
    }

    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxTextLength) {
                onValueChange(it)
            }
        },
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape,
            ),
        enabled = enabled,
        textStyle = KBongTypography.Body1Regular.copy(color = textColor),
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (isValueEmpty) {
                    Text(
                        text = placeholderText,
                        style = KBongTypography.Body1Regular,
                        color = KBongGrayscaleGray10,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    innerTextField()
                    Spacer(modifier = Modifier.weight(1F))
                    Text(
                        text = buildAnnotatedString {
                            append("${value.length}")
                            withStyle(
                                SpanStyle(
                                    color = KBongGrayscaleGray4
                                )
                            ) {
                                append("/${maxTextLength}")
                            }
                        },
                        style = KBongTypography.Label1Regular,
                        color = if (value.isEmpty()) {
                            KBongGrayscaleGray4
                        } else {
                            KBongPrimary
                        }
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewBaseTextField() {
    BaseTextField(
        modifier = Modifier.padding(10.dp),
        value = "a",
        onValueChange = {}
    )
}