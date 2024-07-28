package com.mohaberabi.composelocalization

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService
import androidx.core.os.LocaleListCompat
import java.util.Locale

class LocaleServices(
    private val context: Context,
) {

    private val localManager = context.getSystemService<LocaleManager>()!!

    fun changeLocale(code: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localManager.applicationLocales = LocaleList.forLanguageTags(code)
        } else {
//            Locale.setDefault(Locale(code))

            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(code))
        }
    }


    fun currentLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localManager.applicationLocales[0]?.toLanguageTag()?.split("-")?.firstOrNull()
                ?: AppLocales.English.code
        } else {
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags().split("-").firstOrNull() ?: AppLocales.English.code
//            Locale.getDefault().toLanguageTag().split("-").firstOrNull() ?: AppLocales.English.code
        }
    }
}


@Composable
fun rememberLocaleServices(
): LocaleServices {
    val context = LocalContext.current
    return remember {
        LocaleServices(context)
    }
}