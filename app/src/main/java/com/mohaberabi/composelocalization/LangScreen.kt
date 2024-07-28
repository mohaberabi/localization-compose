package com.mohaberabi.composelocalization

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaberabi.composelocalization.ui.theme.ComposeLocalizationTheme


enum class AppLocales(
    val code: String,
    val label: String
) {
    English("en", "English"),
    Arabic("ar", "عربي"),

}

@Composable
fun LangScreen(
    modifier: Modifier = Modifier,
    currentLangCode: String,
    onLangChange: (String) -> Unit,
) {


    val context = LocalContext.current

    val currentLange by remember {
        mutableStateOf(currentLangCode)
    }
    Scaffold(
        modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {


            Text(
                text = stringResource(R.string.welcome),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp
                )
            )

            AppLocales.entries.forEach {

                    lang ->
                ListItem(
                    modifier = modifier
                        .clickable {
                            onLangChange(lang.code)
                        },
                    headlineContent = {

                    },
                    trailingContent = {
                        RadioButton(
                            selected = currentLange == lang.code,
                            onClick = { onLangChange(lang.code) },
                        )
                    },
                    leadingContent = {

                        Text(text = lang.label)
                    }

                )
            }



            Button(
                onClick = {
                    openAppSettings(context)
                },
            ) {
                Text(
                    text = stringResource(R.string.change_language_from_settings),
                )
            }
        }
    }
}


private fun openAppSettings(context: Context) {

    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts(
            "package", context.packageName,
            null,
        )
    ).also {
        context.startActivity(it)
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewLangScreen() {


    ComposeLocalizationTheme {
        LangScreen(
            currentLangCode = "ar",
        ) {

        }
    }
}