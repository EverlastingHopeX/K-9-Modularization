package com.fsck.k9m_m.ui.settings.import

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fsck.k9m_m.helper.SingleLiveEvent

class SettingsImportResultViewModel : ViewModel() {
    private val importResult = SingleLiveEvent<SettingsImportSuccess>()

    val settingsImportResult: LiveData<SettingsImportSuccess> = importResult

    fun setSettingsImportResult() {
        importResult.value = SettingsImportSuccess
    }
}

object SettingsImportSuccess
