package com.alorma.settings.storage.preferences

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.settings.storage.SettingValueState

@Composable
fun rememberPreferenceIntSettingState(
  key: String,
  defaultValue: Int = 0,
  preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current),
): IntPreferenceSettingValueState {
  return remember {
    IntPreferenceSettingValueState(
      key = key,
      preferences = preferences,
      defaultValue = defaultValue
    )
  }
}

class IntPreferenceSettingValueState(
  private val preferences: SharedPreferences,
  val key: String,
  val defaultValue: Int = 0,
) : SettingValueState<Int> {

  private var _value by mutableStateOf(preferences.getInt(key, defaultValue))

  override var value: Int
    set(value) {
      _value = value
      preferences.edit { putInt(key, value) }
    }
    get() = _value
}