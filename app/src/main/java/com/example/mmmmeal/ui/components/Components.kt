package com.example.mmmmeal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.mmmmeal.R

@Composable
fun CustomOutlinedTextField(
    type: String,
    value: String,
    onValueChangeFun: (String) -> Unit,
    placeHolder: String,
    isPasswordVisible: Boolean,
    onIconClickFun: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChangeFun,
        placeholder = {
            Text(
                placeHolder,
                fontFamily = FontFamily(Font(R.font.baloo_medium))
            )
        },
        visualTransformation = if (type == "password" && !isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        singleLine = true,
        keyboardOptions = if (type == "email") KeyboardOptions(keyboardType = KeyboardType.Email) else KeyboardOptions.Default,
        modifier = Modifier.fillMaxWidth(0.85f),
        maxLines = 1,
        trailingIcon = {
            if (type == "password") {
                val image = if (isPasswordVisible)
                    R.drawable.ic_invisible
                else R.drawable.ic_visible

                IconButton(onClick = onIconClickFun) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            }
        }
    )
}
